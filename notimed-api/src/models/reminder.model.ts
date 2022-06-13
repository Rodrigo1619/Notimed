import { model, Schema } from "mongoose";

const Reminder: Schema = new Schema({
    name: {
        type: String,
        required: true
    },
    prescriptions: [{
        dose: Number,
        hour: Date
    }],
    startDay: {
        type: String,
        required: false
    },
    endDay: {
        type: String,
        required: false
    },
    foodOption: {
        type: Boolean,
        required: false
    },
});

export default model('Reminder', Reminder);