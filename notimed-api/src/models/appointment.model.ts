import { model, Schema } from "mongoose";
//1. creating interface
interface IAppointment extends Document{
    appointmentName:string,
    doctorName:string,
    appointmentDate:string,
    appointmentHour:string,
    address:string,
    additionalNotes:string
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
    }
},
{
    //3.what we don't wanna show to user
    toJSON:{
        transform(doc,ret){
            delete ret.__v
        }
    }
});

export default model('appointment', Appointment);