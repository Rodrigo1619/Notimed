import { Router } from "express";
import validarCampos  from 'src/helpers/handling-errors';
import {check} from 'express-validator'
import { addReminder,deleteReminder,updateReminder,getReminders, getReminder } from "../controllers/reminder-controller";

const router = Router();

//post
router.post('/add/:id', addReminder);

//get
router.get('/:id', getReminders);
router.get('/:id/:id2', getReminder);

//delete
router.delete('/delete/:id/:id2', deleteReminder)

//patch
router.patch('/update/:id/:id2',[
    check('id', 'No es un id valido').isMongoId(),
    validarCampos
],updateReminder);


module.exports = router; 
