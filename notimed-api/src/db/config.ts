import configEnv from "src/config/config";
import mongoose, { ConnectOptions } from "mongoose";
import '../pre-start';

const dbConnection = async() => {
    //const URI = process.env.MONGODB_CNN;
    try {
         mongoose.connect(`${configEnv.mongo_cn}`, {
            useNewUrlParser: true,
            useUnifiedTopology: true
}       as ConnectOptions);
        console.log("Base de datos online");
    } catch (error) {
        throw new Error("La base de datos no pudo ser conectada");
    }
}

export default dbConnection;