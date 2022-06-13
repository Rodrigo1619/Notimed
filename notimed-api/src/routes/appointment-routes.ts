import { Router } from "express";
import { getAppointment, createAppointment, getAppointments } from "../controllers/appointment-controller";

const router = Router();

router.post('/create', createAppointment);

router.get('/appointment', getAppointment);
router.get('/appointments', getAppointments);

module.exports = router; 