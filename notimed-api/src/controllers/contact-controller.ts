import express, { Request, Response } from 'express';
import { validationResult } from 'express-validator';
import { request } from 'http';
import contactModel from '../models/contact.model';
import Contact from '../models/contact.model';
import User from '../models/user.model';


const createContact = async (req: Request, res: Response) => {
    try {
        const errors = validationResult(req);
        if (!errors.isEmpty()) {
            return res.status(400).json({ errors: errors.array() });
        }
        const { name, phoneNumber, address, specialization, startHour, endHour } = req.body //destructuring model CONTACT


        //if the contact exists
        const body = req.body;

        //Datos de usuario
        const { id } = req.params;
        const existingUser = await User.findOne({ _id: id });
        const userInfo = {
            id: existingUser?._id,
        }


        const existingNumber = await Contact.findOne({ phoneNumber: body.phoneNumber });
        const existingContact = await Contact.findOne({ name: body.name });

        if (existingContact) {
            throw { status: 409, message: "You have a contact with this name already" }
        } else if (existingNumber) {
            throw { status: 409, message: "Phone number already exists" }
        } else {
            const newContact = new Contact({
                name: name,
                phoneNumber: phoneNumber,
                address: address,
                specialization: specialization,
                startHour: startHour,
                endHour: endHour,
                user: userInfo.id
            });
            await newContact.save()
            await newContact.populate('user', '_id')
                .then((newContact: any) => {
                    res.status(201).send(newContact);
                })
                .catch((error: any) => {
                    res.status(400).send({
                        message: "Contact cannot be added",
                        error
                    })
                })
        }


    } catch (error) {
        return res
            .status(error.status as number ?? 400)
            .json({ message: error.message ?? JSON.stringify(error) });
    }
}
const deleteContact = async (req: Request, res: Response) => {
    try {
        const errors = validationResult(req);
        if (!errors.isEmpty()) {
            return res.status(400).json({ errors: errors.array() });
        }
        const { id, id2 } = req.params;
        await Contact.findByIdAndDelete({ _id: id, user: id2 });
        return res.status(200).json({ message: "Deleted" })
    } catch (error) {
        return res
            .status(error.status as number ?? 400)
            .json({ message: error.message ?? JSON.stringify(error) });
    }
}
const updateContact = async (req: Request, res: Response) => {
    try {
        const errors = validationResult(req);
        if (!errors.isEmpty()) {
            return res.status(400).json({ errors: errors.array() });
        }
        const { id, id2 } = req.params;
        const { name, phoneNumber, address, specialization, startHour, endHour} = req.body;
        const update = await Contact.findByIdAndUpdate({ _id: id, user: id2 }, {
            name: name,
            phoneNumber: phoneNumber,
            adress: address,
            specialization: specialization,
            startHour: startHour,
            endHour: endHour
        })
        res.status(200).send({ update })
    } catch (error) {
        return res
            .status(error.status as number ?? 400)
            .json({ message: error.message ?? JSON.stringify(error) });
    }
}

const getContacts = async (req: Request, res: Response) => {
    const errors = validationResult(req);
    if (!errors.isEmpty()) {
        return res.status(400).json({ errors: errors.array() });
    }
    const { limit = 5, skip = 0 } = req.query;

    const { id } = req.params;


    const [total, contacts] = await Promise.all([
        Contact.countDocuments({ user: id }),
        Contact.find({ user: id })
            .skip(Number(skip))
            .limit(Number(limit))
    ])

    res.json({ total, contacts });
};

const getContact = async (req: Request, res: Response) => {
    try {
        const errors = validationResult(req);
        if (!errors.isEmpty()) {
            return res.status(400).json({ errors: errors.array() });
        }
        const { id, id2 } = req.params;

        const contact = await Contact.findOne({ _id: id, user: id2 });

        if (!contact)
            return res.status(404).send({ message: "Contact not found" });

        res.send({ contact })
    } catch (error) {
        return res
            .status(error.status as number ?? 400)
            .json({ message: error.message ?? JSON.stringify(error) });
    }
}


export {
    createContact,
    deleteContact,
    updateContact,
    getContacts,
    getContact
};