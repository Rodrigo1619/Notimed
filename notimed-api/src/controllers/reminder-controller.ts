import express,{Request, Response} from 'express';
import reminderModel from '../models/reminder.model';
import Reminder from '../models/reminder.model';


const addReminder = async(req: Request, res: Response)=>{
    try{
        const {name, repeatEvery,hour,dose, startDay, endDay, foodOption} = req.body;
        if(name === '' || repeatEvery === '' || hour ==='' || dose==='' || startDay ==='' || endDay ==='' || foodOption === '')
        throw{ status:400, message: "All fields must be completed"}

        const newReminder = new Reminder({
            name: name,
            repeatEvery: repeatEvery,
            hour:hour,
            dose:dose,
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
        const reminder = await Reminder.findByIdAndDelete(id);
        return res.status(200).json({reminder})
    }catch(error){
        return res
        .status(error.status as number ?? 400)
        .json({message: error.message ?? JSON.stringify(error)});
    }
}

const updateReminder = async(req:Request, res:Response)=>{
    try{
        const{name,prescriptions,startDay,endDay,foodOption}=req.body;
        const update = await Reminder.findByIdAndUpdate(req.params.id,{
            name:name,
            prescriptions:prescriptions,
            startDay:startDay,
            endDay:endDay,
            foodOption:foodOption
        })
        res.status(201).send({update})

    }catch(error){
        return res
        .status(error.status as number ?? 400)
        .json({message: error.message ?? JSON.stringify(error)});
    }
}

const getReminders = async(req: Request, res: Response)=>{
    
    const { limit = 5, skip = 0 } = req.query;

    const query = { estado: true };


    const [total, reminders] = await Promise.all([
        Reminder.countDocuments(query),
        Reminder.find(query)
            .skip(Number(skip))
            .limit(Number(limit))
    ])

    res.json({ total, reminders });
    
    
    //return res.status(200).json(await Reminder.find());
}
const getReminder = async(req:Request, res:Response)=>{
    return res.status(200).json(await Reminder.findOne());
}


export{
    addReminder,
    deleteReminder,
    updateReminder,
    getReminders,
    getReminder
}