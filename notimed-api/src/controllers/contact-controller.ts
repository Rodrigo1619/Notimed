import express,{Request, Response} from 'express';
import { request } from 'http';
import contactModel from '../models/contact.model';
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
            //await newContact.populate('user','_id')
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
const deleteContact = async(req: Request, res: Response)=>{
    const {id} = req.params;
    try{
        const contact = await Contact.findByIdAndDelete(id);
        return res.status(200).json({contact})
    }catch(error){
        return res
        .status(error.status as number ?? 400)
        .json({message: error.message ?? JSON.stringify(error)});
    }
}
const updateContact = async(req:Request,res: Response )=>{
    try{
        const{name,phoneNumber,address,specialization,startHour,endHour,days}=req.body;
        const update = await Contact.findByIdAndUpdate(req.params.id,{
            name:name,
            phoneNumber:phoneNumber,
            adress: address,
            specialization:specialization,
            startHour:startHour,
            endHour:endHour,
            days:days
        })
        res.status(201).send({update})
    }catch(error){
        return res
        .status(error.status as number ?? 400)
        .json({message: error.message ?? JSON.stringify(error)});
    }
} 

const getContacts = async(req: Request, res: Response)=>{
    const { limit = 5, skip = 0 } = req.query;

    const query = { estado: true };


    const [total, contacts] = await Promise.all([
        Contact.countDocuments(query),
        Contact.find(query)
            .skip(Number(skip))
            .limit(Number(limit))
    ])

    res.json({ total, contacts });
};

const getContact =async (req:Request, res: Response) => {
    try {
        const { id } = req.params;

        const contact = await Contact.findOne({ id });

        if (!contact)
            return res.status(404).send({ message: "Contact not found" });

        res.send({ contact })
    } catch (error) {
        return res
            .status(error.status as number ?? 400)
            .json({ message: error.message ?? JSON.stringify(error) });
    }
}


export{
    createContact,
    deleteContact,
    updateContact,
    getContacts,
    getContact
};