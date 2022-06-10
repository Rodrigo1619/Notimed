import express,{Request, Response} from 'express';
import Contact from '../models/contact.model';


const getContact = (req: Request, res: Response)=>{
    res.send('You are at contact route')
}
export{
    getContact
}