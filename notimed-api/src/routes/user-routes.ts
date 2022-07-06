import { Router } from 'express';
import {check} from 'express-validator'
import passport from 'passport';
import { existingUserById } from 'src/helpers/db-validators';
import {getAllUsers, getUser, updateUser, } from "../controllers/user-controller"



const router = Router();


//Todos los usuarios
router.get('/', getAllUsers, passport.authenticate("jwt"));
router.get('/:id', getUser);

router.patch('/:id',  [
    check('id', 'No es un id válido').isMongoId(),
    check('id').custom(existingUserById),
], updateUser);







module.exports = router;

