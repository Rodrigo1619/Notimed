import express, { Request, Response } from 'express';
import appointmentModel from '../models/appointment.model';
import Appointment from '../models/appointment.model';

const createAppointment = async (req: Request, res: Response) => {
    try {
        const { appointmentName, doctorName, appointmentDate, appointmentHour, address, additionalNotes } = req.body;
        if (appointmentName === "" || doctorName === "" || appointmentDate === "" || appointmentHour === "" || address === "")
            throw { status: 400, message: "Fields are empty" }

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
        if (appointment) {
            throw { status: 409, message: "Appointment already exists" }
        }
        else {
            const newAppointment = new Appointment({
                appointmentName: appointmentName,
                doctorName: doctorName,
                appointmentDate: appointmentDate,
                appointmentHour: appointmentHour,
                address: address,
                additionalNotes: additionalNotes
            });
            await newAppointment.save()
                .then((newAppointment: any) => {
                    res.status(200).send({ newAppointment });
                })
                .catch((err: any) => {
                    res.status(400).send({
                        message: 'Appointment cannot be added',
                        err
                    });
                });
        }

    } catch (error) {
        return res
            .status(error.status as number ?? 400)
            .json({ message: error.message ?? JSON.stringify(error) });
    }
}
const deleteAppointment = async(req:Request, res:Response)=>{
    const {id} = req.params;
    try{
        const appointment = await Appointment.findByIdAndDelete(id);
        return res.status(200).json({appointment})
    }catch(error){
        return res
        .status(error.status as number ?? 400)
        .json({message: error.message ?? JSON.stringify(error)});
    }
}
const updateAppointment = async(req:Request, res:Response)=>{
    try{
        const{appointmentName, doctorName, appointmentDate, appointmentHour, adress,additionalNotes} = req.body;
        const update = await Appointment.findByIdAndUpdate(req.params.id,{
            appointmentName:appointmentName,
            doctorName:doctorName,
            appointmentDate:appointmentDate,
            appointmentHour:appointmentHour,
            adress:adress,
            additionalNotes:additionalNotes
        })
        res.status(201).send({update})

    }catch(error){
        return res
        .status(error.status as number ?? 400)
        .json({message: error.message ?? JSON.stringify(error)});
    }
}

const getAppointments = async (req: Request, res: Response) => {
    const { limit = 5, skip = 0 } = req.query;

    const query = { estado: true };


    const [total, appointments] = await Promise.all([
        Appointment.countDocuments(query),
        Appointment.find(query)
            .skip(Number(skip))
            .limit(Number(limit))
    ])

    res.json({ total, appointments });
};

const getAppointment = async (req: Request, res: Response) => {
    try {
        const { id } = req.params;

        const appointment = await Appointment.findOne({ id });

        if (!appointment)
            return res.status(404).send({ message: "appointment not found" });

        res.send({ appointment })
    } catch (error) {
        return res
            .status(error.status as number ?? 400)
            .json({ message: error.message ?? JSON.stringify(error) });
    }
};

export {
    getAppointment,
    deleteAppointment,
    updateAppointment,
    getAppointments,
    createAppointment
}