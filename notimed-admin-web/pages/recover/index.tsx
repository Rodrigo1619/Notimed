import type { NextPage } from 'next'
import Head from 'next/head'
import Image from 'next/image'
import { MdMailOutline, MdLock } from "react-icons/md";
import InputGroup from '../../src/components/Inputs/InputGroup';
import OkButton from '../../src/components/Buttons/OkButton';
import LogInButton from '../../src/components/Buttons/LogInButton';
import RecoverButton from '../../src/components/Buttons/RecoverButton';

const Recover: NextPage = () => {
    return (
        <>
    <form className='w-full h-full px-4 mt-4 mb-8 space-y-5 md:px-16 items-center flex flex-col justify-center'>
    
    <InputGroup  className="focus:outline-none focus:border-blue-600" 
    placeholder="mrroboto@example.com" 
    maxLength="" 
    minLenght="" 
    required="true" 
    label="Ingresa tu correo:"  
    type="text" 
    icon={<MdMailOutline className="absolute w-9 h-9 pl-3 text-onSurface-variant"/>}
    identifier="emaildAdminRegister"/>        
    </form>
    <div className='w-full h-full px-4 mt-4 mb-8 space-y-5 md:px-16 items-center flex flex-col justify-center'>
      <section className='w-full h-auto flex flex-row space-x-8 items-center max-w-[18.75rem] mx-2 justify-center'>
        <RecoverButton text='Recuperar contraseña '/>
      </section>
     
    </div>
    </>
    )
}

export default Recover
