import { Router } from "express";
import { addReminder,getReminders, getReminder } from "../controllers/reminder-controller";

const router = Router();

//post
router.post('/add', addReminder);

//get
router.get('/reminders', getReminders);
router.get('/reminder', getReminder);



module.exports = router; 