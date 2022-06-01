import { model, Schema } from 'mongoose';

const Role: Schema = new Schema({
    
    name: {
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
        required: true
    },
    rol: {
        type: String,
        required: true,
        enum: ["admin", "user"]
    }
});

module.exports = model('Role', Role);