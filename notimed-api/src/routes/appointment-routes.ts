import { Router } from "express";
import validarCampos  from 'src/helpers/handling-errors';
import {check} from 'express-validator'
import { getAppointment,deleteAppointment, updateAppointment,createAppointment, getAppointments } from "../controllers/appointment-controller";

const router = Router();

router.post('/create', createAppointment);

router.get('/:id', getAppointment);
router.get('/', getAppointments);

//delete
router.delete('/delete/:id', deleteAppointment);

//patch
router.patch('/update/:id',[
    check('id', 'No es un id valido').isMongoId(),
    validarCampos
],updateAppointment);

module.exports = router; 