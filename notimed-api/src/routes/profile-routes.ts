import { Router } from "express";
import { getProfile } from "../controllers/profile-controller";

const router = Router();

router.get("/profile", getProfile);

module.exports = router;

