import { Router } from "express";
import {check} from 'express-validator'
import { exists } from "fs";
import { addReminder,deleteReminder,updateReminder,getReminders, getReminder } from "../controllers/reminder-controller";

const router = Router();
// const {name, repeatEvery,hour,dose, foodOption} = req.body;
//post
router.post('/add/:id', [
    check('id', 'No es un id valido').isMongoId(),
    check('name', 'Ingrese el nombre del recordatorio').exists().not().isEmpty(),
    check('name', 'Tiene que ser un string').exists().isString(),

    check('repeatEvery', 'Ingrese el intervalo del recordatorio').exists().not().isEmpty(),
    check('repeatEvery', 'Tiene que ser un entero').exists().isInt(),

    check('hour', 'Ingrese la hora del recordatorio').exists().not().isEmpty(),
    check('hour', 'Tiene que ser un string').exists().isString(),

    check('dose', 'Ingrese la dósis').exists().not().isEmpty(),
    check('dose', 'Tiene que ser un entero').exists().isInt(),

    check('foodOption', 'Ingrese la opción de comida').exists().not().isEmpty(),
    check('foodOption', 'Tiene que ser booleano').exists().isBoolean()
    
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
