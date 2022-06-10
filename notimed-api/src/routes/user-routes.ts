import express, { Router, NextFunction, Request, Response } from 'express';
import path from "path";
import {register, getAllUsers} from "../controllers/user-controller"

/* const express = require("express"); */

const router = Router()

//post
//router.post('/signup', register)

//get
router.get('/', getAllUsers);


module.exports = router;

