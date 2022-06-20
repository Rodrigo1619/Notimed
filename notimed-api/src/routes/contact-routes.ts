import { Router } from "express";
import {createContact,deleteContact,updateContact,getContacts, getContact} from "../controllers/contact-controller"
import validarCampos  from 'src/helpers/handling-errors';
import {check} from 'express-validator'
const router = Router();

//get
router.get('/', getContacts);
router.get('/:id',getContact);
//post
router.post('/create',createContact);

//delete
router.delete('/delete/:id', deleteContact);
//patch
router.patch('/update/:id',[
    check('id', 'No es un id valido').isMongoId(),
    validarCampos
],updateContact);

module.exports = router; 