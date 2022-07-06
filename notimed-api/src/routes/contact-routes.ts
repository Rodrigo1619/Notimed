import { Router } from "express";
import {createContact,deleteContact,updateContact,getContacts, getContact} from "../controllers/contact-controller"
import validarCampos  from 'src/helpers/handling-errors';
import {check} from 'express-validator'
const router = Router();

//get
router.get('/:id', getContacts);
router.get('/:id/:id2', [
    check('id1', 'No es un id valido').isMongoId(),
    check('id2', 'No es un id valido').isMongoId()
], getContact);
//post
router.post('/create/:id',createContact);

//delete
router.delete('/delete/:id/:id2', [
    check('id1', 'No es un id valido').isMongoId(),
    check('id2', 'No es un id valido').isMongoId()
], deleteContact);
//patch
router.patch('/update/:id/:id2',[
    check('id1', 'No es un id valido').isMongoId(),
    check('id2', 'No es un id valido').isMongoId()
],updateContact);

module.exports = router; 