import './pre-start'; // Must be the first import
import logger from 'jet-logger';
import server from './server';
import mongoose from 'mongoose';
import app from './server';
import dbConnection from './db/config';
const cors = require('cors');

const PORT = process.env.PORT

//cors
app.use(cors());

//db connection
const dbConnect = async ()=>{
    await dbConnection();
    console.log("---------------------------------");
}

dbConnect();

// Start server
server.listen(PORT, () => {
    logger.info('Express server started on port: ' + PORT);
});


const db = mongoose.connection;
db.on('error', console.error.bind(console, 'connection error:')); // enlaza el track de error a la consola (proceso actual)
db.once('open', () => {
  console.log('connected'); // si esta todo ok, imprime esto
});