//Imports
import express from 'express';
import passport, { session } from 'passport';
import path from 'path'
require("../src/middlewares/passport")
import dbConnection from './db/config';
// Constants
var logger = require("morgan");
const cors = require('cors');
const app = express();
const usersPath = '/users';
const identityPath = '/identity';
const reminderPath = '/reminders';
const contactPath = '/contacts';
const appointmentPath = '/appointments'
//Para tomar los datos del body en formato json
var bodyParser = require('body-parser');
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: true }));

app.use(express.static(path.join(__dirname, '../views')))

//db connection
const dbConnect = async ()=>{
    await dbConnection();
    console.log("---------------------------------");
}

//Conectar base de datos
dbConnect();
//cors
app.use(cors());
app.use(logger("dev"));
app.use(passport.initialize())

//rutas definidas
app.use(identityPath, require('./routes/identity-routes'));
app.use(passport.authenticate("jwt", { session: false }))
app.use(usersPath, require('./routes/user-routes'));
app.use(reminderPath, require('./routes/reminder-routes'));
app.use(contactPath, require('./routes/contact-routes'));
app.use(appointmentPath, require('./routes/appointment-routes'));

export default app;
