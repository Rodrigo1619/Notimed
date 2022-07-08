import axios from 'axios';
import type { NextPage } from 'next'
import Head from 'next/head'
import { useEffect, useState } from 'react';
import { MdMailOutline } from "react-icons/md";
import InputGroup from '../../src/components/Inputs/InputGroup';
import RecoverNotimed from '../../src/components/svg/RecoverNotimed';
import Top from '../../src/components/svg/Top';

const Recover: NextPage = () => {

    const [email, setEmail] = useState({
        email: ''
    })

    const [success, setSuccess] = useState(false);
 
    const forgotPassword = "http://localhost:5000/identity/forgot-password";

   

    const onSubmit = async (e: React.FormEvent) => {
        e.preventDefault()
        const  loginFormData = new FormData();
        loginFormData.append("email", email.email )
        try {
           const response = await axios.post(forgotPassword, {
            data: loginFormData,
            headers: { "Content-Type": "multipart/form-data" },
           });
           console.log(response)
        } catch (error) {
            
        }
        
    }
  
    return (
        <>
        {success ? (
                <section>
                <h1>Your password link reset has been sent</h1>
                <br />
                <p>
                <a href="#">Go to home</a></p>
                </section>
            ): (
           <section>
             <Head>
                <title> Recuperar contraseña </title>
            </Head>
            <Top title='Recuperar contraseña' icon={<RecoverNotimed />} className="h-[10rem]"/>
            <form  className='w-full h-full px-4 mt-4 mb-8 space-y-5 md:px-16 items-center flex flex-col justify-center' onSubmit={onSubmit}>
                <InputGroup name={email.email} className="focus:outline-none focus:border-blue-600"
                    onChange={(e:any) => setEmail(
                      e.target.value
                   )}
                    value= {email.email}
                    placeholder="mrroboto@example.com"
                    minLength={4}
                    required={true}
                    label="Ingresa tu correo:"
                    type="text"
                    icon={<MdMailOutline className="absolute w-9 h-9 pl-3 text-onSurface-variant" />}
                    identifier="email" />
                <button className="bg-primaryContainer text-onPrimaryContainer labelLarge w-full rounded-full h-10 max-w-[18.75rem]" type="submit">
                    <div className="hover:bg-onPrimaryContainerState-hover focus:bg-onPrimaryContainerState-focus
                px-7 py-2 w-full rounded-full">
                        Recuperar contraseña
                    </div>
                </button>
            </form>
           </section>
            )}
        </>
    )
}


export default Recover
