import express,{Request, Response} from 'express';
import Appointment from '../models/appointment.model';


const getAppointment = (req: Request, res: Response)=>{
    res.send('You are at appointment route');
}
export{
    getAppointment
}