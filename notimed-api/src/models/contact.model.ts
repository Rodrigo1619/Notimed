import { model, Schema, Document } from "mongoose";


//1. creating an interface as a representation of a document
interface IContact extends Document{
    name:string,
    phoneNumber:string,
    address:string,
    specialization:string,
    startHour:string,
    endHour:string,
    days:object
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
    days: {
        required: true,
        type: Object,
        options: {
            Monday: Boolean,
            Tuesday: Boolean,
            Wednesday: Boolean,
            Thursday: Boolean,
            Friday: Boolean,
            Saturday: Boolean,
            Sunday: Boolean,
        }
    }
},
//3 delete what we don't wanna show to user
{
    toJSON:{
        transform(doc,ret){
            delete ret.__v
            
        },
    }
});


export default model('contact', Contact);