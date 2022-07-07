import { model, Schema, Document, ObjectId } from "mongoose";

//1. creating interface
interface IReminder extends Document{
    name:string,
    repeatEvery:number,
    hour:string,
    dose:number,
    rangeDate:string,
    foodOption:boolean,
    user: ObjectId
}

//2. creating schema
const Reminder: Schema = new Schema<IReminder>({
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
    rangeDate:{
        type: String,
        required: true
    },
    foodOption: {
        type: Boolean,
        required: false
    },
    user: {
        type: Schema.Types.ObjectId,
        ref: 'User'
    }
},
{ //delete what we don't wanna show to user
    toJSON:{
        transform(doc,ret){
            delete ret.__v
            delete ret.user.__v
            delete ret.user.name
            delete ret.user.lastName
            delete ret.user.email
            delete ret.user.rol
            delete ret.user.birthday
            delete ret.user.gender
        }
    }
});

export default model('Reminder', Reminder);