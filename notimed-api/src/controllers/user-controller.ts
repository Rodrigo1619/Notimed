import User from '../models/user.model';
import { NextFunction, Request, Response } from 'express'
import jwt, { JwtPayload } from 'jsonwebtoken'
import nodemailer from 'nodemailer';
import bcrypt from 'bcrypt';
import configEnv from '../config/config';
import { config } from 'dotenv';

const register = async (req: Request, res: Response, next: NextFunction) => {
    try {
        const { name, lastName, email, password, birthday, gender, rol } = req.body;
        if (name === "" || email === "" || password === "" || lastName === "" || birthday === "" || gender === "")
            throw { status: 400, message: "Fields are empty" }

        const body = req.body
        const existingUser = await User.findOne({ email: body.email });

        if (existingUser) {
            throw { status: 409, message: "user already exists" }
        } else {

            const myPasssword = body.password;
            const hash = bcrypt.hashSync(myPasssword, bcrypt.genSaltSync(10));

            const newUser = new User({
                name: name,
                lastName: lastName,
                email: email,
                password: hash,
                birthday: birthday,
                gender: gender,
                rol: rol
            });
            await newUser.save()
                .then((newUser: any) => {

                    res.status(200).send({ newUser });
                })
                .catch((err: any) => {
                    res.status(400).send({
                        message: 'Error al registar el usuario',
                        err
                    });
                });
        }
    }
    catch (error) {
        return res
            .status(error.status as number ?? 400)
            .json({ message: error.message ?? JSON.stringify(error) });
    }
}


const login = async (req: Request, res: Response) => {
    try {

        const { email, password } = req.body;

        const user = await User.findOne({ email })
        if (!user) {
            return res.status(404).send({
                message: 'Usuario no encontrado'
            });
        }

        if (!bcrypt.compareSync(password, user.password)) {
            return res.status(403).send({
                message: 'Datos incorrectos'
            });
        }

        const payload = {
            id: user._id
        }

        const token = jwt.sign(payload, configEnv.secret_key as string);
        res.status(200).json({ token });

    } catch (error) {
        return res
            .status(error.status as number ?? 400)
            .json({ message: error.message ?? JSON.stringify(error) });
    }
}

const getAllUsers = async (req: Request, res: Response) => {

    const { limit = 5, skip = 0 } = req.query;

    const query = { estado: true };


    const [total, users] = await Promise.all([
        User.countDocuments(query),
        User.find(query)
            .skip(Number(skip))
            .limit(Number(limit))
    ])

    res.json({ total, users });
}


const getUser = async (req: Request, res: Response) => {
    try {
        const { id } = req.params;

        const user = await User.findOne({ id });

        if (!user)
            return res.status(404).send({ message: "User not found" });

        res.send({ user })
    } catch (error) {
        return res
            .status(error.status as number ?? 400)
            .json({ message: error.message ?? JSON.stringify(error) });
    }

}

const updateUser = async (req: Request, res: Response) =>{
    try {
        const { name, lastName, birthday, gender } = req.body;
        const update = await User.findByIdAndUpdate(req.params.id, {
            name: name,
            lastName: lastName,
            birthday: birthday,
            gender: gender
        });

    

        res.status(201).send({update});
    } catch (err: any) {
        return res
                .status(err.status as number ?? 400)
                .json({ message: err.message ?? JSON.stringify(err) });
        }
    }

const whoami = async (req: Request, res: Response) => {
    try {
        const auth = req.headers['authorization']

        if(!auth) throw { status: 403, message: 'Empty token'}

        const [bearer, token] = auth.split(' ')

        if(bearer !== "Bearer" || token === "")
            throw { status: 401, message: "Invalid token"}

        const payload: JwtPayload = 
            jwt.verify(token, configEnv.secret_key as string) as JwtPayload
        
        const user = await User.findById({ _id: payload.id })

        return res.status(200).json({ message: "Is authorized", content: user})
    } catch (err: any) {
        return res
                .status(err.status as number ?? 400)
                .json({ message: err.message ?? JSON.stringify(err) });
    }
} 

const resetPassword = async (req: Request, res: Response) => {
    const {id, token} = req.params;
    const existingUser = await User.findOne({_id: id});
    const userInfo = {
        id: existingUser?._id,
        password: existingUser?.password
    }
    if (!userInfo.id) {
        res.send('Invalid id');
        return;
        
    }

    const secret:string = configEnv.secret_key + userInfo.password!;

    const {password, password2} = req.body;

    try {
        const payload = jwt.verify(token, secret);
        if(password !== password2){
            res.send("Passwords don't match");
            return;
        }

        userInfo.password = password;

        const myPasssword = userInfo.password;
        const hash = bcrypt.hashSync(myPasssword!, bcrypt.genSaltSync(10));
        const update = await User.findByIdAndUpdate(id, {
            password: hash
        });

        res.send({update});  
    } catch (err: any) {
        return res
                .status(err.status as number ?? 400)
                .json({ message: err.message ?? JSON.stringify(err) });
    }


}

const forgotPassword = async (req: Request, res:Response) =>{

    const { email} = req.body;
    const existingUser = await User.findOne({ email: email });
    if(!existingUser)
        return res.send("User not exist");

   const transporter =  nodemailer.createTransport({
        host: 'smtp.gmail.com',
        port: 465,
        secure: true,
        auth: {
            user: `${configEnv.user_mailer}`,
            pass: `${configEnv.pass_mailer}`
        }
    });
    transporter.verify().then(()=>{
        console.log('Ready for send emails')
    });

    const userInfo = {
        password: existingUser.password,
        id: existingUser._id
    }

    const secret = configEnv.secret_key + userInfo.password;
    const payload = {
        email: email,
        id: userInfo.id
    }

    const token = jwt.sign(payload, secret, {expiresIn: '15m'});

    const link = `http://localhost:3000/identity/reset-password/${userInfo.id}/${token}`

    const info = await transporter.sendMail({
        from: `'Notimed' <${configEnv.user_mailer}>`,
        to: `${email}`,
        subject: 'Reset password☠️',
        text: `Recover link: ${link}`
    });

    

    res.send('Password reset link has been sent to your email!');
}

export {
    register,
    login,
    getAllUsers,
    getUser,
    updateUser,
    forgotPassword,
    resetPassword,
    whoami
}

