import '../pre-start';


const configEnv = {
    port: process.env.PORT,
    mongo_cn: process.env.MONGODB_CNN,
    secret_key: process.env.SECRET_KEY
}

export default configEnv;