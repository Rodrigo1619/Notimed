import { Router } from "express";
import { addReminder,deleteReminder,getReminders, getReminder } from "../controllers/reminder-controller";

const router = Router();

//post
router.post('/add', addReminder);

//get
router.get('/reminders', getReminders);
router.get('/reminder', getReminder);

//delete
router.delete('delete', deleteReminder)


module.exports = router; 