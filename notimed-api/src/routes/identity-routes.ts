
import express from 'express';
import { login, register } from '../controllers/user-controller';


const loginRouter = express();

loginRouter.post("/signup",  register);

loginRouter.post("/signin", login);

module.exports = loginRouter;





