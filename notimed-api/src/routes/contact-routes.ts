import { Router } from "express";
import {createContact,getContact} from "../controllers/contact-controller"

const router = Router();

//get
router.get('/contact', getContact);

//post
router.post('/createContact',createContact);

module.exports = router; 