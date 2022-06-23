import { response } from "express";
import { validationResult } from "express-validator";

//Se muestran los errores al hacer una petición
const validarCampos = (req:Request, next:any) => {

    const errors = validationResult(req);
    if (!errors.isEmpty()) {
        response.status(400).send(errors);      
    }
    next();
}


export default validarCampos;