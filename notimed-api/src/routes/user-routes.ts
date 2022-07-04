import { Router } from 'express';
import {check} from 'express-validator'
import { existingUserById } from 'src/helpers/db-validators';
import validarCampos  from 'src/helpers/handling-errors';
import {register, getAllUsers, getUser, updateUser, recoverPassword} from "../controllers/user-controller"


const router = Router();


//Todos los usuarios
router.get('/', getAllUsers);
router.get('/:id', getUser);

router.patch('/:id',  [
    check('id', 'No es un id válido').isMongoId(),
    check('id').custom(existingUserById),
    validarCampos
], updateUser);     

router.post("/recover-password", recoverPassword);



module.exports = router;

