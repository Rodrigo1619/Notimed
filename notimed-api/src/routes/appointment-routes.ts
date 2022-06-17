import { Router } from "express";
import { getAppointment,deleteAppointment, createAppointment, getAppointments } from "../controllers/appointment-controller";

const router = Router();

router.post('/create', createAppointment);

router.get('/appointment', getAppointment);
router.get('/', getAppointments);

//delete
router.delete('/delete', deleteAppointment);

module.exports = router; 