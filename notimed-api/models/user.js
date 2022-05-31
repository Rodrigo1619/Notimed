
const {Schema, model} = require('mongoose');

const UserSchema = Schema({
    name: {
        type: String,
        required: [true, 'El nombre es obligatorio'],
        
    },
    correo: {
        type: String,
        required: [true, 'El nombre es obligatorio'],
        unique: true,
        
        
    },
    password: {
        type: String,
        required: [true, 'La contraseña es obligatoria'],
       
        
    },
    img: {
        type: String,
        
    },
    rol: {
        type: String,
        required: [true, 'El rol es requerido'],
        //enum: ['ADMIN_ROLE', 'USER_ROLE']
    },
    estado: {
        type: Boolean,
        default: true
    },
    google: {
        type: Boolean,
        default: false
    }
    
},
{
    toJSON: {
      transform(doc, ret) {
        delete ret.password;
        delete ret.__v;
      },
    },
  });

  module.exports = model('User', UserSchema);


/* UserSchema.methods.toJson = function () {

    //const {__v, password, ...usuario} = this.toObject();
    const showResponse = this.toObject();

    delete showResponse.password;
    delete showResponse.__v;

    return usuario;
    
}
 */
