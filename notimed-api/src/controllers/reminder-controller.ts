import express,{Request, Response} from 'express';
import Reminder from '../models/reminder.model';


const getReminder = (req: Request, res: Response)=>{
    res.send('You are at reminder route')
}
export{
    getReminder
}