import { model, Schema, Document, ObjectId } from "mongoose";


//1. creating an interface as a representation of a document
interface IContact extends Document{
    name:string,
    phoneNumber:string,
    address:string,
    specialization:string,
    startHour:string,
    endHour:string,
    days:object,
    user:ObjectId
}

//2 creating schema corresponding to interface
const Contact: Schema = new Schema<IContact>({
    name: {
        type: String,
        required: true
    },
    phoneNumber: {
        type: String,
        required: true
    },
    address:{
        type: String,
        required: true
    },
    specialization: {
        type: String,
        required: true
    },
    startHour: {
        type: String,
        required: true
    },
    endHour: {
        type: String,
        required: true
    },
    user: {
        type: Schema.Types.ObjectId,
        ref: 'User'
    }
},
//3 delete what we don't wanna show to user
{
    toJSON:{
        transform(doc,ret){
            delete ret.__v
            delete ret.user.__v
            delete ret.user.name
            delete ret.user.lastName
            delete ret.user.email
            delete ret.user.rol
            delete ret.user.birthday
            delete ret.user.gender
        },
    }
});


export default model('contact', Contact);