import { model, Schema } from "mongoose";

const Contact: Schema = new Schema({
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
});

module.exports = model('contact', Contact);