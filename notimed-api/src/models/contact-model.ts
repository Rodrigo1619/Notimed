

import { model, Schema } from "mongoose";

//Contact Schema
const Contact: Schema = new Schema({
    contactName: {
        type: String,
        required: true
    },
    phoneNumber: {
        type: String,
        required: true
    },
    location:{
        type: String,
        required: true
    },
    specialization: {
        type: String,
        required: true
    },
    startTime: {
        type: String,
        required: true
    },
    endTime: {
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