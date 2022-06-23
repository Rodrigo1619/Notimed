import User from '../models/user.model';

 //Verificar si el correo existe
 const existingEmail = async(correo = '') => {

    const existeEmail = await User.findOne({correo});
    if (existeEmail) {
        throw new Error('Este correo ya existe');
    };
 }
 //Verificar si el usuario existe mediante el id
 const existingUserById = async( id:any ) => {
    const existingUser = await User.findOne({_id:id});
    console.log(existingUser);
    if (!existingUser) {
        throw new Error('El id no es válido');
    };
 }
export  { 
    existingEmail,
    existingUserById
};