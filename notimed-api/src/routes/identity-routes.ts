
import { check, validationResult } from 'express-validator';
import express from 'express';
import { login, register, whoami, forgotPassword, resetPassword} from '../controllers/user-controller';

const loginRouter = express();

loginRouter.post("/signup", [
        check('name', 'Ingrese un nombre ').exists().not().isEmpty(),
        check('lastName', 'Ingrese un apellido').exists().not().isEmpty(),
        check('email', 'Ingrese un email válido').exists().isEmail(),
        check('password', 'La contraseña debe de tener 8 carácteres como mínimo').exists().isLength({min:8}),
        check('birthday', 'Ingrese su cumpleaños').exists().not().isEmpty(),
        check('gender', 'Ingrese su género').exists().not().isEmpty()
    
], register);

loginRouter.post("/signin", [
    check('email', 'Ingrese un email válido').exists().isEmail().not().isEmpty(),
    check('password', 'Ingrese su contraseña').exists().not().isEmpty()
], login);

loginRouter.get('/whoami', whoami);
loginRouter.post("/forgot-password", [
    check('email', 'Ingrese un email valido').exists().isEmail().not().isEmpty()

], forgotPassword);

loginRouter.patch("/reset-password/:id/:token", [
    check('id').exists().isMongoId(),
    check('password', 'Ingrese una contraseña válida').exists().not().isEmpty().isLength({min:8}),
    check('passwor2', 'Ingrese una contraseña válida').exists().not().isEmpty().isLength({min:8})
], resetPassword);

module.exports = loginRouter;





