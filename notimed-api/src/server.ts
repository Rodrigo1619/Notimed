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
app.use(loginPath, require('../src/routes/login'));

export default app;
