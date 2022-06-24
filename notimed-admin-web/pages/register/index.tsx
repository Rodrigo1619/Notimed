import type { NextPage } from 'next'
import Head from 'next/head'
import Image from 'next/image'
import { MdMailOutline, MdLock, MdPerson, MdCalendarToday} from "react-icons/md";
import InputGroup from '../../src/components/Inputs/InputGroup';
import OkButton from '../../src/components/Buttons/OkButton';
import LogInButton from '../../src/components/Buttons/LogInButton';
import RegisterButton from '../../src/components/Buttons/RegisterButton';

const Register: NextPage = () => {
    return (
        <>
    <form className='w-full h-full px-4 mt-4 mb-8 space-y-5 md:px-16 items-center flex flex-col justify-center'>
    <InputGroup  className="focus:outline-none focus:border-blue-600" 
    placeholder="mrroboto" 
    maxLength="" 
    minLenght="" 
    required="true" 
    label="Ingresa tu nombre:"  
    type="text" 
    icon={<MdPerson className="absolute w-9 h-9 pl-3 text-onSurface-variant"/>}
    identifier="nameAdminRegister"/>
    
    <InputGroup  className="focus:outline-none focus:border-blue-600" 
    placeholder="mrroboto@example.com" 
    maxLength="" 
    minLenght="" 
    required="true" 
    label="Ingresa tu correo:"  
    type="text" 
    icon={<MdMailOutline className="absolute w-9 h-9 pl-3 text-onSurface-variant"/>}
    identifier="emaildAdminRegister"/>

    <InputGroup  className="focus:outline-none focus:border-blue-600" 
    placeholder="dd/mm/yyyy" 
    maxLength="" 
    minLenght="" 
    required="true" 
    label="Ingresa tu fecha de nacimiento:"  
    type="text" 
    icon={<MdCalendarToday className="absolute w-9 h-9 pl-3 text-onSurface-variant"/>}
    identifier="birthdayAdminRegister"/>

    <InputGroup  className="focus:outline-none focus:border-blue-600" 
    placeholder="contraseña" 
    maxLength="" 
    minLenght="" 
    required="true" 
    label="Ingresa tu contraseña:"  
    type="password" 
    icon={<MdLock className="absolute w-9 h-9 pl-3 text-onSurface-variant"/>}
    identifier="passwordAdminRegister"/>

    <div className='relative mt-2 flex items-center  space-x-4 mx-3 ' id='genderSelection'>
            <input placeholder='Masculino' type='radio' name='gender' id='genderMale'/> 
            <div>Masculino</div>
            <input placeholder='Femenino' type='radio' name='gender' id='genderFemale'/>
            <div>Femenino</div> 
    </div>
        
    </form>
    <div className='w-full h-full px-4 mt-4 mb-8 space-y-5 md:px-16 items-center flex flex-col justify-center'>
      <section className='w-full h-auto flex flex-row space-x-8 items-center max-w-[18.75rem] mx-2 justify-center'>
        <RegisterButton text='Registrate'/>
      </section>
      <section className='w-full h-auto flex flex-row space-x-2 items-center max-w-[18.75rem] mx-2 justify-center'>
        <span className=''>¿Ya tienes una cuenta?</span>
        <span className='text-blue-600 hover:cursor-pointer hover:underline'>Inicia sesión</span>
      </section>
    </div>
    </>
    )
}

export default Register
