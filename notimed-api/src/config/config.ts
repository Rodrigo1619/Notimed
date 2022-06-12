import '../pre-start';


const configEnv = {
    port: process.env.PORT,
    mongo_cn: process.env.MONGODB_CNN,
    google_client_id: process.env.GOOGLE_CLIENT_ID,
    google_client_secret: process.env.GOOGLE_CLIENT_SECRET
}

export default configEnv;