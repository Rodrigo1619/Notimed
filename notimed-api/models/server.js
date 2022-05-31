const express = require("express");

const cors = require('cors');
const { dbConnection } = require("../db/config");


class Server {

    constructor() {
        this.app = express();
        this.port = process.env.PORT;

        this.usersPath = '/api/users'

         //Coneccion de base de datos
         this.conectarDb();

        // Middlewares
        
        this.middlewares();

        //Rutas de mi app
        this.routes();

       
    }


    async conectarDb(){
        await dbConnection();
    }
    

    middlewares(){

        //CORS

        this.app.use(cors());

        //Lectura y parseo del body
        this.app.use(express.json());

        //Directorio público
        this.app.use(express.static('public'));
    }

    routes(){

        //rutas definidas
        this.app.use(this.usersPath, require('../routes/users.routes'))
        
    }



    listen(){
        this.app.listen(this.port, ()=> {
            console.log('Servidor corriendo en: ', this.port)
        })
    }
}

module.exports = Server;