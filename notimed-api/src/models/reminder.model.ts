import { model, Schema, Document, ObjectId } from "mongoose";

//1. creating interface
interface IReminder extends Document{
    name:string,
    repeatEvery:number,
    hour:string,
    dose:number,
    startDay:string,
    endDay:string,
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
    user: {
        type: Schema.Types.ObjectId,
        ref: 'User'
    }
},
{ //delete what we don't wanna show to user
    toJSON:{
        transform(doc,ret){
            delete ret.__v
        }
    }
});

export default model('Reminder', Reminder);