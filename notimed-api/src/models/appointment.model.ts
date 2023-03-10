import { model, ObjectId, Schema } from "mongoose";
//1. creating interface
interface IAppointment extends Document{
    appointmentName:string,
    doctorName:string,
    appointmentDate:string,
    appointmentHour:string,
    address:string,
    additionalNotes:string,
    user:ObjectId
}
//2.creating schema
const Appointment: Schema = new Schema<IAppointment>({
    
    appointmentName: {
        type: String,
        required: true
    },
    doctorName: {
        type: String,
        required: true
    },
    appointmentDate: {
        type: String,
        required: true
    },
    appointmentHour: {
        type: String,
        required: true
    },
    address: {
        type: String,
        required: true
    },
    additionalNotes: {
        type: String,
        required: false
    },
    user: {
        type: Schema.Types.ObjectId,
        ref: 'User'
    }
},
{
    //3.what we don't wanna show to user
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
        }
    }
});

export default model('appointment', Appointment);