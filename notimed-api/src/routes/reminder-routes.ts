import { Router } from "express";
import {check} from 'express-validator'
import { addReminder,deleteReminder,updateReminder,getReminders, getReminder } from "../controllers/reminder-controller";

const router = Router();
// const {name, repeatEvery,hour,dose, startDay, endDay, foodOption} = req.body;
//post
router.post('/add/:id', [
    check('id', 'No es un id valido').isMongoId(),
    check('repeatEvery', 'Ingrese el nombre del recordatorio').exists().not().isEmpty(),
    check('name', 'Ingrese el nombre del recordatorio').exists().not().isEmpty(),
    check('name', 'Ingrese el nombre del recordatorio').exists().not().isEmpty(),
    check('name', 'Ingrese el nombre del recordatorio').exists().not().isEmpty(),
    
], addReminder);

//get
router.get('/:id', [
    check('id', 'No es un id valido').isMongoId(), 
], getReminders);

router.get('/:id/:id2', [
    check('id', 'No es un id valido').isMongoId(),
    check('id2', 'No es un id valido').isMongoId()
], getReminder);

//delete
router.delete('/delete/:id/:id2',[
    check('id', 'No es un id valido').isMongoId(),
    check('id2', 'No es un id valido').isMongoId()
], deleteReminder)

//patch
router.patch('/update/:id/:id2',[
    check('id', 'No es un id valido').isMongoId(),
    check('id2', 'No es un id valido').isMongoId(),

],updateReminder);


module.exports = router; 
