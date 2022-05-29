
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
        type: Date,
        required: true
    },

    appointmentHour: {
        type: Date,
        required: true
    },
    location: {
        type: String,
        required: true
    },
    additionalNotes: {
        type: String,
        required: true
    }
});

module.exports = model('appointment', Appointment);