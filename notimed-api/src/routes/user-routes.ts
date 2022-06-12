import express, { Router, NextFunction, Request, Response } from 'express';
import path from "path";
import {register, getAllUsers} from "../controllers/user-controller"


const router = Router();


//Todos los usuarios
router.get('/', getAllUsers);


module.exports = router;

