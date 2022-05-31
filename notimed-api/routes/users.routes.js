


const {Router} = require('express');
const { check } = require('express-validator');
const { usersGet, usersPost, usersPut, usersPatch, userDelete } = require('../controllers/users.controller');
const {esRolValido, existingEmail, existingUserById} = require('../helpers/db-validators');
const { validarCampos } = require('../middlewares/validar-campos');
const Rol = require('../models/rol');

const router = Router();

router.get('/', usersGet );

router.put('/:id', [
    check('id', 'No es un id válido').isMongoId(),
    check('id').custom(existingUserById),
   //check('rol').custom(esRolValido), 
    validarCampos  
], usersPut );   
 
router.post('/', [
    check('name', 'El nombre es Obligatorio').not().isEmpty(),
    check('password', 'El password tiene que tener más de 8 carácteres').isLength({min:8}),
    check('correo', 'El correo no es válido').isEmail(),
    check('correo').custom(existingEmail), 
    //check('rol', 'No es un rol válido').isIn(['ADMIN_ROL', 'USER_ROL']),
    check('rol').custom(esRolValido),
    validarCampos 
], usersPost ); 

router.patch('/', usersPatch );

router.delete('/:id', [
    check('id', 'No es un id válido').isMongoId(),
    check('id').custom(existingUserById),
    validarCampos
], userDelete );

module.exports = router; 
