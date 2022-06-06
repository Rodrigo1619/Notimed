
import path from 'path';
import express, { NextFunction, Request, Response } from 'express';
import dbConnection from './db/config';

const cors = require('cors');




// Constants
const app = express();
const usersPath = '/api/users';

import cookieParser from 'cookie-parser';
const session = require('express-session');
const passport = require('passport');

const LocalStrategy = require('passport-local').Strategy

var bodyParser = require('body-parser');
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: true }));

app.use(cookieParser('mi ultra secreto'));


// required for passport session
app.use(session({
  secret: 'secrettexthere',
  saveUninitialized: true,
  resave: true
}));

// Init passport authentication 
app.use(passport.initialize());
// persistent login sessions 
app.use(passport.session());

passport.use(new LocalStrategy(function(username:String, password:String, done:any){
     if(username === 'juan' && password==='1234')
     return done(null, {id:1, name: 'Memo'});

     done(null, false);
}));

passport.serializeUser((user: any, done:any)=>{
    done(null, user.id);
});

passport.deserializeUser((id:Number, done:any)=>{
    done(null, {id:1, name: 'Memo'});

});


// Set views dir
const viewsDir = path.join(__dirname, 'views');
app.set('views', viewsDir);




// Serve index.html file
app.get('/login', (_: Request, res: Response) => {
    res.sendFile('login.html', {root: viewsDir});
}); 

app.post('/login', passport.authenticate('local',{
    successRedirect: "/",
    failureRedirect: "/login"
})); 


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

export default app;
