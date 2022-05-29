
import { model, Schema } from "mongoose";

const Reminder: Schema = new Schema({
    
    reminderName: {
        type: String,
        required: true
    },
    prescription: {
        type: String,
        required: true
    },
    startDay: {
        type: String,
        required: false
    },

    endDay: {
        type: Date,
        required: false
    },
    foodOption: {
        type: Boolean,
        required: false
    },
});

module.exports = model('Reminder', Reminder);