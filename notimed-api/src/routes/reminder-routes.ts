import { Router } from "express";
import { getReminder } from "../controllers/reminder-controller";

const router = Router();

router.get('/reminder', getReminder);

module.exports = router; 