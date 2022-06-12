import { Router } from "express";
import {getContact} from "../controllers/contact-controller"

const router = Router();

router.get('/contact', getContact);

module.exports = router; 