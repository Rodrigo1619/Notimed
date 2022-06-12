import { Router } from "express";
import {createContact,getContacts, getContact} from "../controllers/contact-controller"

const router = Router();

//get
router.get('/contacts', getContacts);
router.get('/contact',getContact);
//post
router.post('/create',createContact);

module.exports = router; 