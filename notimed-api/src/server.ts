//Imports
import path from 'path';
import express, { NextFunction, Request, Response } from 'express';
import dbConnection from './db/config';
import passport from 'passport';
// Constants
const cors = require('cors');
const app = express();
const usersPath = '/users';
const identityPath = '/identity';
const reminderPath = '/reminders';
const contactPath = '/contacta';
const appointmentPath = '/appointments'

//Para tomar los datos del body en formato json
var bodyParser = require('body-parser');
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: true }));

//db connection
const dbConnect = async ()=>{
    await dbConnection();
    console.log("---------------------------------");
}

//Conectar base de datos
dbConnect();
//cors
app.use(cors());


//rutas definidas

app.use(usersPath, require('../src/routes/user-routes'));
app.use(identityPath, require('../src/routes/identity-routes'));
app.use(reminderPath, require('../src/routes/reminder-routes'));
app.use(contactPath, require('../src/routes/contact-routes'));
app.use(appointmentPath, require('../src/routes/appointment-routes'));
export default app;
