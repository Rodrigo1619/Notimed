const mongoose = require('mongoose');

const dbConnection = async() =>{

    const URI = process.env.MONGODB_CNN;

    try {

       await mongoose.connect(URI, {
           useNewUrlParser: true,
           useUnifiedTopology: true,
          /*  useCreateIndex: true,
           useFindAndModify: false */ //No longer supported
       });

       console.log("Base de datos online");
        
    } catch (error) {
        console.log(error);
        throw new Error("Error a la hora de iniciar la base de datos");
    }

}


module.exports = {
    dbConnection
}