import { model, Schema } from "mongoose";

const Reminder: Schema = new Schema({
    name: {
        type: String,
        required: true
    },
    repeatEvery:{
        type:Number,
        required:true
    },
    hour:{
        type: String,
        required: true
    },
    dose:{
        type: Number,
        required: true
    },
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