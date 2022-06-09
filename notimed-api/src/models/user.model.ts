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
    required: true 
  },
  password: { 
    type: String, 
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
  
}).pre<IUser>('save', async function(next) {
  const hash = await bcrypt.hash(this.password, 10);
  this.password = hash;
  next();
})

UserSchema.methods.isValidPassword = async function (password:string) {
  const user = this;
  const compare = await bcrypt.compare(password, user.password);
  return compare;
}

// 3. Create a Model.
const User = model<IUser>('User', UserSchema);

export default User;