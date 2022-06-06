import User from '@models/user.model';
import { NextFunction, Request, Response } from 'express'
//import jwt, { JwtPayload } from 'jsonwebtoken'

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
            const saltRounds = 10;
            const salt = bcrypt.genSaltSync(saltRounds);
            const hash = bcrypt.hashSync(myPasssword, salt);
            console.log(hash);

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
          .then((item:Object)=>{
              res.status(200).send({item} )
          })
            .catch((err:any) => {
                res.status(400).send(err);
            });
        }
        

        

    // Encriptar contraseña

   /* const saltRounds = 10;
   let myPlainPassword = password;
 
   bcrypt.genSalt(saltRounds, function(err:any, salt:any){
       bcrypt.hash(myPlainPassword, salt, function(err:any, hash:any){
        console.log(hash);
       });
   }); */
        
    } catch (error) {
        console.log(error);
    }
}

   
    //---

    

   
    //login
    //Esto ya es con el token y el .env y no se como le han hecho ahi
    //whoAmI
    //este es el ultimo
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
    getAllUsers
}


/* 
 try {

            const {name, lastName, email, password,birthday, gender, rol} = req.body;


                const salt = 10;
                const hashedPassword = await bcrypt.hash(password, salt);
                next();
                const newUser = new user({
                    name,
                    lastName,
                    email,
                    password: hashedPassword,
                    birthday,
                    gender,
                    rol
                });

                
                await newUser.save();

                res.send({newUser});

    

                //Encriptar la constraseña con bcrypt

        
        }catch(err:anyy){
            return res 
            .status(err:anytus as number ?? 400)
            .json({message: err:anysage ?? JSON.stringify(err:any        } */