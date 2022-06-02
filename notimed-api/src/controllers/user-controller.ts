import { Request, Response } from 'express'
import jwt, { JwtPayload } from 'jsonwebtoken'
import User from '../models/user.model'

const userController ={

    register: async (req: Request, res: Response)=>{
        try {
            const {name, lastName, email, password,birthday, gender, rol} = req.body as any
            if(email === "" || password === "")
                throw { status: 400, message: "Fields are empty"}

                const existingUser = await User.findOne({ email})

                if(existingUser)
                throw{ status: 400, message: "User alredy exists"}

                const newUser = new User({
                    name,
                    lastName,
                    email,
                    password,
                    birthday,
                    gender,
                    rol
                })
                await newUser.save()

                return res.status(201).json({message: "User created", content: newUser})
        }catch(err: any){
            return res 
            .status(err.status as number ?? 400)
            .json({message: err.message ?? JSON.stringify(err)})
        }
    
    },
    //login
    //Esto ya es con el token y el .env y no se como le han hecho ahi
    //whoAmI
    //este es el ultimo
    getAll: async( req: Request, res: Response)=>{
        return res.status(200).json(await User.find())
    }

}
export default userController