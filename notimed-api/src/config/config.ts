import '../pre-start';


const configEnv = {
    port: process.env.PORT,
    mongo_cn: process.env.MONGODB_CNN,
    secret_key: process.env.SECRET_KEY,
    user_mailer: process.env.USER_MAILER,
    pass_mailer: process.env.PASS_MAILER
}

export default configEnv;