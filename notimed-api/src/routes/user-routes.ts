import express, { NextFunction, Request, Response } from 'express';
import path from "path";

/* const express = require("express"); */

const userRoutes = () => {
    const app = express();
    const viewsDir = path.join(__dirname, 'views');
    app.set('views', viewsDir);

    app.get('/login', (_: Request, res: Response) => {
        
    });
}

export default userRoutes;