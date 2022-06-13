import express,{Request, Response} from 'express';
import Profile from '../models/profile.model';


const getProfile = (req: Request, res: Response)=>{
    res.send('You are at profile route')
}
export{
    getProfile
}