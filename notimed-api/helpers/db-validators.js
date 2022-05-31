const Rol = require('../models/rol');
const User = require('../models/user');

const esRolValido =  async (rol ='') => {
    const existeRol = await Rol.findOne({rol:rol});
    if (!existeRol) {
        throw new Error(`El rol ${rol} no es válido`);
        
    }

} 


 //Verificar si el correo existe

 const existingEmail = async(correo = '') => {

    const existeEmail = await User.findOne({correo});
    if (existeEmail) {
        throw new Error('Este correo ya existe');
    };
 }


 const existingUserById = async( id ) => {

    const existingUser = await User.findOne({_id:id});
    console.log(existingUser);
    if (!existingUser) {
        throw new Error('El id no es válido');
    };
 }
 
 


module.exports ={ 
    esRolValido,
    existingEmail,
    existingUserById
};