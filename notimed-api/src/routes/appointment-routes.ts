import { Router } from "express";
import { getAppointment } from "../controllers/appointment-controller";

const router = Router();

router.get('/appointment', getAppointment);

module.exports = router; 