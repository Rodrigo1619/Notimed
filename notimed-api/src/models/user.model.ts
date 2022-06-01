// const { default: mongoose } = require('mongoose');
// const {Schema, model} = require('mongoose');

// const ObjectID = mongoose.Schema.Types.ObjectId;

import { model, Schema } from "mongoose";

const User: Schema = new Schema({
    name: { 
        type: String,
        required: true
    },
    lastName: {
        type: String,
        required: true
    },
    email: {
        type: String,
        required: true,
        unique: true
    },
    password: {
        type: String,
        required: true
    },
    birthday: {
        type: Date,
        required: true
    },
    gender: {
        type: String,
        required: true,
        enum: ["female", "male"]
    },
    rol: {
        type: String,
        required: true,
        enum: ["admin", "user"]
    }
});

export default User