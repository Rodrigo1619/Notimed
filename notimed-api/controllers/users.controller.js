const {response} = require('express');

const User = require('../models/user');

const bcryptjs = require('bcryptjs');
const { default: mongoose } = require('mongoose');
//const { validationResult } = require('express-validator');


const usersGet = async(req, res = response)=>{

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


const usersPost = async (req, res = response)=>{
    

    const {name, correo, password, rol} = req.body;

    const user = new User({
        name, 
        correo, 
        password, 
        rol
    });

   

    // Encriptar contraseña

    const salt = bcryptjs.genSaltSync();
    user.password = bcryptjs.hashSync(password, salt);

    //---

    await user.save();

    res.json({
        user  
    });
}

const usersPut = async(req, res = response)=>{

    const {id} = req.params;

    const { _id, password, google, ...resto} = req.body;

    // TODO validar contra la base de datos
 
   
    if (password) {
        // Encriptar contraseña
        const salt = bcryptjs.genSaltSync();
        resto.password = bcryptjs.hashSync(password, salt);
    }
    
    const usuario = await User.findByIdAndUpdate(id, resto, {new: true});

    /*
    if( !mongoose.Types.ObjectId.isValid(id) ) {
        return res.status(400).json({
            status_code: 0,
            error_msg: "Require Params Missing",
          });
    } */

 
    res.status(200).json({ 
        
        'msg': 'Users Put - controller',
        usuario
        
    }) 
}

const usersPatch = (req, res = response)=>{

    res.json({
        'id': 1,
        'msg': 'Users Patch - controller'
    })
}
const userDelete = async (req, res = response)=>{

    const {id} = req.params;


    //Borrar físicamente
    //const usuario = await User.findByIdAndDelete(id)

    //Borrar pero siempre en la base de datos

    const user = await User.findByIdAndUpdate(id, {estado:false}, {new:true})

    res.json({
        user
    })
} 
 
module.exports = {
    usersGet,
    usersPut,
    usersPost,
    usersPatch,
    userDelete
}
   