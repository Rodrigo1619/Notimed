import { Router } from "express";
import { createContact, deleteContact, updateContact, getContacts, getContact } from "../controllers/contact-controller"
import { check } from 'express-validator'
const router = Router();


//get
router.get('/:id', getContacts);
router.get('/:id/:id2', [
    check('id1', 'No es un id valido').isMongoId(),
    check('id2', 'No es un id valido').isMongoId()
], getContact);
//post
router.post('/create/:id', [
    check('id', 'No es un id valido').isMongoId(),
    check('name', 'Ingrese elnombre del contacto').exists().not().isEmpty(),
    check('name', 'Tiene que ser un string').exists().isString(),

    check('phoneNumber', 'Ingrese el número del doctor').exists().not().isEmpty(),
    check('phoneNumber', 'Tiene que ser string').exists().isString(),

    check('address', 'Ingrese el lugar de cita').exists().not().isEmpty(),
    check('address', 'Tiene que ser un string').exists().isString(),

    check('specialization', 'Ingrese la especialidad del doctor').exists().not().isEmpty(),
    check('specialization', 'Tiene que ser un string').exists().isString(),

    check('startHour', 'Ingrese la especialidad del doctor').exists().not().isEmpty(),
    check('startHour', 'Tiene que ser un string').exists().isString(),

    check('endHour', 'Ingrese la especialidad del doctor').exists().not().isEmpty(),
    check('endHour', 'Tiene que ser un string').exists().isString(),

    check('days', 'Ingrese los días').exists().not().isEmpty(),
    check('days', 'Tiene que ser objeto').exists().isObject(),

], createContact);

//delete
router.delete('/delete/:id/:id2', [
    check('id1', 'No es un id valido').isMongoId(),
    check('id2', 'No es un id valido').isMongoId()
], deleteContact);
//patch
router.patch('/update/:id/:id2', [
    check('id1', 'No es un id valido').isMongoId(),
    check('id2', 'No es un id valido').isMongoId()
], updateContact);

module.exports = router; 