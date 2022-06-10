//Imports
import path from 'path';
import express, { NextFunction, Request, Response } from 'express';
import dbConnection from './db/config';
import passport from 'passport';
// Constants
const cors = require('cors');
const app = express();
const usersPath = '/notimed';
const loginPath = '/login';
const reminderPath = '/reminder';
const contactPath = '/contact';
const appointmentPath = '/appointment';

//Para tomar los datos del body en formato json
var bodyParser = require('body-parser');
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: true }));



// Set views dir
const viewsDir = path.join(__dirname, 'views');
app.set('views', viewsDir);



//Middlewares

//Passport
app.use(passport.initialize());



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
app.use(loginPath, require('../src/routes/login'));
app.use(reminderPath, require('../src/routes/reminder-routes'));
app.use(contactPath, require('../src/routes/contact-routes'));
app.use(appointmentPath, require('../src/routes/appointment-routes'));
export default app;
