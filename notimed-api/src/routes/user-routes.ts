import express, { Router, NextFunction, Request, Response } from 'express';
import path from "path";
import userController from '../controllers/user-controller';

/* const express = require("express"); */

const baseRouter = Router()

//post
baseRouter.post('/register', userController.register)

//get
baseRouter.get('/', userController.getAll)


const userRoutes = Router()
userRoutes.use('/users', baseRouter)

export default userRoutes;