import { Router } from "express";
import {check} from 'express-validator'
import { getAppointment,deleteAppointment, updateAppointment,createAppointment, getAppointments } from "../controllers/appointment-controller";

const router = Router();

//const { appointmentName, doctorName, appointmentDate, appointmentappointmentDate, address, additionalNotes } = req.body;

router.post('/create/:id', [
    check('id', 'No es un id valido').isMongoId(),
    check('appointmentName', 'Ingrese elnombre de la cita').exists().not().isEmpty(),
    check('appointmentName', 'Tiene que ser un string').exists().isString(),

    check('doctorName', 'Ingrese el nombre del doctor').exists().not().isEmpty(),
    check('doctorName', 'Tiene que ser string').exists().isString(),

    check('appointmentDate', 'Ingrese la hora de la cita').exists().not().isEmpty(),
    check('appointmentDate', 'Tiene que ser un string').exists().isString(),

    check('appointmentHour', 'Ingrese la hora de la cita').exists().not().isEmpty(),
    check('appointmentHour', 'Tiene que ser un string').exists().isString(),

    check('address', 'Ingrese dirección').exists().not().isEmpty(),
    check('address', 'Tiene que ser string').exists().isString(),

    check('additionalNotes', 'Tiene que ser string').isString()
], createAppointment);

router.get('/:id', [
    check('id', 'No es un id valido').isMongoId()
], getAppointments);
router.get('/:id/:id2', [
    check('id', 'No es un id valido').isMongoId(),
    check('id2', 'No es un id valido').isMongoId()
],  getAppointment);

//delete
router.delete('/delete/:id/:id2', [
    check('id', 'No es un id valido').isMongoId(),
    check('id2', 'No es un id valido').isMongoId()
],deleteAppointment);

//patch
router.patch('/update/:id/:id2',[
    check('id', 'No es un id valido').isMongoId(),
    check('id2', 'No es un id valido').isMongoId(),
],updateAppointment);

module.exports = router; 

