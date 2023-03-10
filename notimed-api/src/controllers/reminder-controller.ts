import express, { Request, Response } from 'express';
import User from '../models/user.model';
import Reminder from '../models/reminder.model';
import { validationResult } from 'express-validator';


const addReminder = async (req: Request, res: Response) => {
    try {
        const errors = validationResult(req);
        if (!errors.isEmpty()) {
            return res.status(400).json({ errors: errors.array() });
        }
        const { id } = req.params;
        const { name, repeatEvery, hour, dose, startDate, endDate, foodOption } = req.body;

        const existingUser = await User.findOne({ _id: id });
        const userInfo = {
            id: existingUser?._id,
        }

        const newReminder = new Reminder({
            name: name,
            repeatEvery: repeatEvery,
            hour: hour,
            dose: dose,
            startDate: startDate,
            endDate: endDate,
            foodOption: foodOption,
            user: userInfo.id
        });
        await newReminder.save();
        await newReminder.populate('user', '_id -__v');

        return res.status(201).json({ content: newReminder });

    } catch (error) {
        return res
            .status(error.status as number ?? 400)
            .json({ message: error.message ?? JSON.stringify(error) });
    }

}
const deleteReminder = async (req: Request, res: Response) => {
    try {
        const errors = validationResult(req);
        if (!errors.isEmpty()) {
            return res.status(400).json({ errors: errors.array() });
        }

        const { id, id2 } = req.params;
        await Reminder.findByIdAndDelete({ _id: id, user: id2 });
        return res.status(200).json({ message: "Deleted" })
    } catch (error) {
        return res
            .status(error.status as number ?? 400)
            .json({ message: error.message ?? JSON.stringify(error) });
    }
}

const updateReminder = async (req: Request, res: Response) => {
    try {

        const errors = validationResult(req);
        if (!errors.isEmpty()) {
            return res.status(400).json({ errors: errors.array() });
        }

        const { id, id2 } = req.params;
        const { name, hour, startDate, endDate, dose, foodOption, repeatEvery } = req.body;
        const update = await Reminder.findByIdAndUpdate({ _id: id, user: id2 }, {
            name: name,
            repeatEvery: repeatEvery,
            hour: hour,
            dose: dose,
            startDate: startDate,
            endDate: endDate,
            foodOption: foodOption,
        })
        res.status(200).send({ update })

    } catch (error) {
        return res
            .status(error.status as number ?? 400)
            .json({ message: error.message ?? JSON.stringify(error) });
    }
}

const getReminders = async (req: Request, res: Response) => {
    try {
        const errors = validationResult(req);
        if (!errors.isEmpty()) {
            return res.status(400).json({ errors: errors.array() });
        }

        const { id } = req.params;
        const { limit = 5, skip = 0 } = req.query;

        const [total, reminders] = await Promise.all([
            Reminder.countDocuments({ user: id }),
            Reminder.find({ user: id })
                .skip(Number(skip))
                .limit(Number(limit))
        ])

        res.status(200).json({ total, reminders });

    } catch (error) {
        console.log(error);
    }

}
const getReminder = async (req: Request, res: Response) => {
    try {
        const errors = validationResult(req);
        if (!errors.isEmpty()) {
            return res.status(400).json({ errors: errors.array() });
        }

        const { id, id2 } = req.params;

        const reminder = await Reminder.find({ _id: id, user: id2 });

        if (!reminder)
            return res.status(404).send({ message: "Reminder not found" });

        res.status(200).json({ reminder })
    } catch (error) {
        return res
            .status(error.status as number ?? 400)
            .json({ message: error.message ?? JSON.stringify(error) });
    }
}


export {
    addReminder,
    deleteReminder,
    updateReminder,
    getReminders,
    getReminder
}