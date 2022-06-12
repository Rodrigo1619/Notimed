import { Router } from "express";
import {createContact,getContacts} from "../controllers/contact-controller"

const router = Router();

//get
router.get('/contacts', getContacts);

//post
router.post('/create',createContact);

module.exports = router; 