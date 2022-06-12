import { model, Schema } from "mongoose";

const Appointment: Schema = new Schema({
    
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
        required: true
    }
});

export default model('appointment', Appointment);