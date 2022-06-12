

import User from '@models/user.model';
import { NextFunction, Request, Response } from 'express'
import jwt, { JwtPayload } from 'jsonwebtoken'

import bcrypt from 'bcrypt';
import configEnv from 'src/config/config';

const register = async (req: Request, res: Response, next: NextFunction)=>{
    try {
        const {name, lastName, email, password, birthday, gender, rol} = req.body;
        if(name === "" || email === "" || password === ""|| lastName === "" || birthday === "" || gender === "") 
        throw { status: 400, message: "Fields are empty"} 

        const body = req.body
        const existingUser = await User.findOne({email: body.email}); 
 
        if(existingUser){
        throw{ status: 400, message: "user already exists"}        
        }else{

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
          .then((newUser:any)=>{

             res.status(200).send({newUser});
          })
            .catch((err:any) => {
                res.status(400).send({
                    message: 'Error al registar el usuario',
                    err
                });
            });
    } 
    }
    catch (error) {
        console.log(error);
    }
}
  

const login = async (req: Request, res: Response)=>{
    try {    

        const {email, password} = req.body;

        const user = await User.findOne({email})
            if (!user) {
                return res.status(401).send({
                    message: 'Usuario no encontrado'
                });
            }

            if (!bcrypt.compareSync(password, user.password)) {
                return res.status(401).send({
                    message: 'Datos incorrectos'
                });  
            }

            const payload = {
                id: user._id
            }

            const token = jwt.sign(payload, configEnv.secret_key as string);
            console.log(token)
            res.status(200).json({token});
           
    } catch (error) {
        console.log(error);
    }
}





const getAllUsers =  async(req: Request,  res: Response)=>{

        const {limit=5, skip=0} = req.query;
    
        const query = {estado:true};
    
      
        const[ total, users] = await Promise.all([
            User.countDocuments(query),
            User.find(query)
                .skip(Number(skip))
                .limit(Number(limit))
        ]) 
    
        res.json({total, users});
    }

export {
    register, 
    login,
    getAllUsers

}

