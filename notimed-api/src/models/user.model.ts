import { Schema, model} from 'mongoose';

// 1. Create an interface representing a document in MongoDB.
interface IUser {
  name: string;
  lastName: string;
  email: string;
  password: string;
  birthday: string;
  gender: string;
  rol: string;
}

// 2. Create a Schema corresponding to the document interface.
const userSchema = new Schema<IUser>({
  name: { type: String, required: true },
  lastName: { type: String, required: true },
  email: { type: String, required: true },
  password: { type: String, required: true },
  birthday: { type: String, required: true },
  gender: { type: String,
    enum: ["female", "male"],
     required: true },
  rol: { type: String,
    enum: ["user", "admin"],
    default: 'user',
     required: true }
  
});

// 3. Create a Model.
const User = model<IUser>('User', userSchema);

export default User;