import express,{Request, Response} from 'express';
import Appointment from '../models/appointment.model';

const createAppointment = async (req: Request, res: Response)=>{
    try{
        const { appointmentName, doctorName, appointmentDate, appointmentHour, address, additionalNotes} = req.body;
        if( appointmentName === "" || doctorName === "" || appointmentDate === "" || appointmentHour === "" || address === "" || additionalNotes === "")
        throw{status: 400, message: "Fields are empty"}

        //if the appointment is already in the database
        const body = req.body;
        const appointment = await Appointment.findOne
        ({
            appointmentName: body.appointmentName,
            doctorName: body.doctorName, 
            address: body.address,
            appointmentDate: body.appointmentDate, 
            appointmentHour: body.appointmentHour
        });
        if(appointment){
        throw{status: 400, message: "Appointment already exists"}
        }
        else{
        const newAppointment = new Appointment({
            appointmentName:  appointmentName,
            doctorName: doctorName,
            appointmentDate: appointmentDate,
            appointmentHour: appointmentHour,
            address: address,
            additionalNotes: additionalNotes
        });
        await newAppointment.save()
        .then((newAppointment:any)=>{
            res.status(200).send({newAppointment});
        })
        .catch((err:any) => {
            res.status(400).send({
                message: 'Appointment cannot be added',
                err
            });
        });
    }

    }catch (error)
    {
        return res
        .status(error.status as number ?? 400)
        .json({message: error.message ?? JSON.stringify(error)});
    }
}

const getAppointments = async (req: Request, res: Response)=>{
    return res.status(200).json(await Appointment.find());
};

const getAppointment = async (req: Request, res: Response)=>{
    return res.status(200).json(await Appointment.findOne());
};

export{
    getAppointment,
    getAppointments,
    createAppointment
}