import { Router } from "express";
import {createContact,deleteContact,getContacts, getContact} from "../controllers/contact-controller"

const router = Router();

//get
router.get('/contacts', getContacts);
router.get('/contact',getContact);
//post
router.post('/create',createContact);

//delete
router.delete('/delete', deleteContact);
module.exports = router; 