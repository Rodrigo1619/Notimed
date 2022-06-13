import express,{Request, Response} from 'express';
import Contact from '../models/contact.model';


const createContact = async(req: Request, res: Response)=>{
    try{
        const{name,phoneNumber,address,specialization,startHour,endHour,days} = req.body //destructuring model CONTACT
        if(name === '' || phoneNumber === '' || address === '' || specialization === '' || startHour === '' || endHour === '' || days === '')
        throw{ status:400, message: "All fields must be completed"};

        //if the contact exists
        const body = req.body;
        const existingNumber = await Contact.findOne({phoneNumber: body.phoneNumber});
        const existingContact = await Contact.findOne({name: body.name});

        if(existingContact){
            throw{ status: 400, message: "You have a contact with this name already"}
        }else if(existingNumber){
            throw{ status: 400, message: "Phone number already exists"}
        }else{ 
            const newContact = new Contact({
                name: name,
                phoneNumber: phoneNumber,
                address: address,
                specialization: specialization,
                startHour: startHour,
                endHour: endHour,
                days: days
            });
            await newContact.save()
            .then((newContact: any)=>{
                res.status(200).send(newContact);
            })
            .catch((error:any)=>{
                res.status(400).send({
                    message: "Contact cannot be added",
                    error
                })
            })
        }
        

    }catch(error){
        return res
        .status(error.status as number ?? 400)
        .json({message: error.message ?? JSON.stringify(error)});
    }
}

const getContacts = async(req: Request, res: Response)=>{
    return res.status(200).json(await Contact.find());
};

const getContact =async (req:Request, res: Response) => {
    return res.status(200).json(await Contact.findOne());
}
//patch
export{
    createContact,
    getContacts,
    getContact
};