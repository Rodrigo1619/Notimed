import { Schema, model, Document} from 'mongoose';
import bcrypt from 'bcrypt';

// 1. Create an interface representing a document in MongoDB.
 interface IUser extends Document {
  name: string;
  lastName: string;
  email: string;
  password: string;
  birthday: string;
  gender: string;
  rol: string;
} 

// 2. Create a Schema corresponding to the document interface.
const UserSchema = new Schema<IUser>({
  name: { 
    type: String,
     required: true 
    },
  lastName: {
     type: String, 
     required: true 
    },
  email: { 
    type: String, 
    unique: true,
    required: true 
  },
  password: { 
    type: String, 
    select: true,
    required: true 
  },
  birthday: { 
    type: String, 
    required: true }
    ,
  gender: { 
    type: String,
    enum: ["female", "male"],
    required: true },
  rol: { 
    type: String,
    enum: ["user", "admin"],
    default: 'user',
    required: true }
  
},
{//Para que no aparezcan estos parámetros al hacer la petición
  toJSON: {
    transform(doc, ret) {
      delete ret.password;
      delete ret.__v;
      //delete ret.rol;
    },
  }
});


// 3. Create a Model.
export default model('User', UserSchema);

//export default User;