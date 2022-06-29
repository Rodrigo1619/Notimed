import type { NextPage } from 'next'
import Head from 'next/head'
import Image from 'next/image'
import { MdMailOutline, MdLock } from "react-icons/md";
import InputGroup from '../src/components/Inputs/InputGroup';
import OkButton from '../src/components/Buttons/OkButton';
import LogInButton from '../src/components/Buttons/LogInButton';
import RegisterButton from '../src/components/Buttons/RegisterButton';

const Home: NextPage = () => {
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
    identifier="emailAdmin"/>
    
    <InputGroup  className="focus:outline-none focus:border-blue-600" 
    placeholder="contraseña" 
    maxLength="" 
    minLenght="" 
    required="true" 
    label="Ingresa tu contraseña:"  
    type="password" 
    icon={<MdLock className="absolute w-9 h-9 pl-3 text-onSurface-variant"/>}
    identifier="passwordAdmin"/>
    </form>
    <div className='w-full h-full px-4 mt-4 mb-8 space-y-5 md:px-16 items-center flex flex-col justify-center'>
    <span className='h-auto w-auto mx-2 justify-center items-center text-blue-600 hover:cursor-pointer hover:underline'>
    ¿Olvidaste tu contraseña?
    </span>
      <section className='w-full h-auto flex flex-row space-x-8 items-center max-w-[18.75rem] mx-2 justify-center'>
        <LogInButton text='Inicia Sesión'/>
      </section>
      <span className='h-auto w-auto hover:underline mx-2'>
    ¿No tienes cuenta?
    </span>
    <div className='w-full h-[1px] bg-blue-600 mx-2'/>
      <section className='w-full h-auto flex flex-row space-x-8 items-center max-w-[18.75rem] mx-2 justify-center'>
        <RegisterButton text='Registrate'/>
      </section>
    </div>
    </>
  )
}

export default Home
