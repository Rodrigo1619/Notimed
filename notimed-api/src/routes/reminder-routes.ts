import { Router } from "express";
import validarCampos  from 'src/helpers/handling-errors';
import {check} from 'express-validator'
import { addReminder,deleteReminder,updateReminder,getReminders, getReminder } from "../controllers/reminder-controller";

const router = Router();

//post
router.post('/add', addReminder);

//get
router.get('/', getReminders);
router.get('/:id', getReminder);

//delete
router.delete('/delete/:id', deleteReminder)

//patch
router.patch('/update/:id',[
    check('id', 'No es un id valido').isMongoId(),
    validarCampos
],updateReminder);


module.exports = router; 