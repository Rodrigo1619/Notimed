import express,{Request, Response} from 'express';
import reminderModel from '../models/reminder.model';
import Reminder from '../models/reminder.model';


const addReminder = async(req: Request, res: Response)=>{
    try{
        const {name, prescriptions, startDay, endDay, foodOption} = req.body;
        if(name === '' || prescriptions === '' || startDay ==='' || endDay ==='' || foodOption === '')
        throw{ status:400, message: "All fields must be completed"}

        const newReminder = new Reminder({
            name: name,
            prescriptions: prescriptions,
            startDay: startDay,
            endDay: endDay,
            foodOption: foodOption
        });
        await newReminder.save()
        .then((newReminder:any)=>{
            res.status(200).send(newReminder)
        })
        .catch((error: any)=>{
            res.status(400).send({
                message: "Reminder cannot be added",
                error
            })
        })




    }catch(error){
        return res
        .status(error.status as number ?? 400)
        .json({message: error.message ?? JSON.stringify(error)});
    }

}
const deleteReminder = async(req:Request, res:Response)=>{
    const {id} = req.params;
    try{
        reminderModel.deleteOne({_id: id});
        return res.status(204).json
    }catch(error){
        console.log(error);
    }
}

const getReminders = async(req: Request, res: Response)=>{
    return res.status(200).json(await Reminder.find());
}
const getReminder = async(req:Request, res:Response)=>{
    return res.status(200).json(await Reminder.findOne());
}


export{
    addReminder,
    deleteReminder,
    getReminders,
    getReminder
}