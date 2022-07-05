import { Router } from "express";
import validarCampos  from 'src/helpers/handling-errors';
import {check} from 'express-validator'
import { getAppointment,deleteAppointment, updateAppointment,createAppointment, getAppointments } from "../controllers/appointment-controller";

const router = Router();

router.post('/create:id', [
    check('id', 'No es un id valido').isMongoId()
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
    check('id2', 'No es un id valido').isMongoId()
],updateAppointment);

module.exports = router; 

