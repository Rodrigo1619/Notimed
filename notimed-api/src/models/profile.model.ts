import { model, Schema } from "mongoose";

const Profile: Schema = new Schema({
    name:{
        type: String,
        required: true
    },
    lastName:{
        type: String,
        required: true
    },
    email: {
        type: String,
        required: true
    },
    birthday: {
        type: String,
        required: true
    },
});

export default model("profile", Profile)