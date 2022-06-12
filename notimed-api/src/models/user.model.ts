import { Schema, model, Document} from 'mongoose';
import bcrypt from 'bcrypt';

// 1. Create an interface representing a document in MongoDB.
/* interface IUser extends Document {
  name: string;
  lastName: string;
  email: string;
  password: string;
  birthday: string;
  gender: string;
  rol: string;
} */

// 2. Create a Schema corresponding to the document interface.
const UserSchema = new Schema({
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
  
}).pre('save', function(next) {
  const hash = bcrypt.hashSync(this.password, bcrypt.genSaltSync(10));
  this.password = hash;
  next();
})

/* UserSchema.methods.comparePassword = function(password:string, cb:any){
  bcrypt.compare(password, this.password)
}; */
  

// 3. Create a Model.
export default model('User', UserSchema);

//export default User;