import User from '@models/user.model';
import { NextFunction, Request, Response } from 'express'
import jwt, { JwtPayload } from 'jsonwebtoken'

const bcrypt = require('bcrypt');
const express = require('express');




const register = async (req: Request, res: Response, next: NextFunction)=>{
    try {
        
        /* if(name === "" || email === "" || password === ""|| lastName === "" || birthday === "" || gender === "") 
        throw { status: 400, message: "Fields are empty"} */

        const body = req.body
        const existingUser = await User.findOne({email: body.email}); 
 
        if(existingUser){
        throw{ status: 400, message: "user already exists"}        
        }else{

            const myPasssword = body.password;
            const hash = bcrypt.hashSync(myPasssword, bcrypt.genSaltSync(10));
            
            const newUser = new User({
                name: req.body.name,
                lastName: req.body.lastName,
                email: req.body.email,
                password: hash,
                birthday: req.body.birthday,
                gender: req.body.gender,
                rol: req.body.rol
            });
          await newUser.save()
          .then((newUser:any)=>{
            const payload = {
                id: newUser._id,
                rol: newUser.rol,
            }
            const token = jwt.sign(payload, "NfpyfOXRlt" );

        res.status(200).send({token});
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

    const password = req.body.password
    const email = req.body.email;

    User.findOne({email: email}, async(user:any)=>{
        const validate = await bcrypt.compare(password, user.password);
        if (!validate) {
            res.status(403).send({message: "Datos incorrectos"})
        } else {
            res.status(200).send({message: 'Has iniciado sesión'})
        }

    });        
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
