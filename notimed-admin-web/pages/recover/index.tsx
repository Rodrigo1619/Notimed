import axios from 'axios';
import type { NextPage } from 'next'
import Head from 'next/head'
import { useEffect, useState } from 'react';
import { MdMailOutline } from "react-icons/md";
import InputGroup from '../../src/components/Inputs/InputGroup';
import RecoverNotimed from '../../src/components/svg/RecoverNotimed';
import Top from '../../src/components/svg/Top';
import { forgotPassword } from '../../src/services/IdentityService';
import { useRouter } from 'next/router';
import { ToastContainer, toast } from 'react-toastify';

const Recover: NextPage = () => {   

    const router = useRouter();
    const onSubmit = async (e: React.FormEvent) => {
        e.preventDefault()

        const formData = new FormData(e.target as HTMLFormElement);
        const body = Object.fromEntries(formData);

        try {
            const response = await forgotPassword(body)

            if (response.status == 200) {
                router.push("/")
                
            }

        } catch (error) {
            const { response }: any = error

            switch (response.status) {
                case 404:
                    toast("El usuario ingresado no existe", { type: "error" })
                    break;
                case 400:
                    toast("Revisa los datos ingresados", { type: "error" })
                    break;
                case 403:
                    toast("Revisa tus credenciales", { type: "error" })
                    break;
                default:
                    break;
            }
        }

    }
  
    return (
        <>

             <Head>
                <title> Recuperar contraseña </title>
            </Head>
            <Top title='Recuperar contraseña' icon={<RecoverNotimed />} className="h-[10rem]"/>
            <form  className='w-full h-full px-4 mt-4 mb-8 space-y-5 md:px-16 items-center flex flex-col justify-center' onSubmit={onSubmit}>
                <InputGroup  className="focus:outline-none focus:border-blue-600"                
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
           </>
    )
}


export default Recover
