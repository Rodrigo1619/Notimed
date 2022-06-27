import type { NextPage } from 'next'
import { useRouter } from 'next/router'
import Head from 'next/head'
import { MdMailOutline, MdLock } from "react-icons/md";
import InputGroup from '../src/components/Inputs/InputGroup';
import LogInButton from '../src/components/Buttons/LogInButton';
import RegisterButton from '../src/components/Buttons/RegisterButton';
import Top from '../src/components/svg/Top';
import LoginNotimed from '../src/components/svg/LoginNotimed';
import React from 'react';
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import { signIn } from '../src/services/IdentityService';

const Home: NextPage = () => {
  const router = useRouter()


  const onSubmit = async (e: React.FormEvent) => {
    e.preventDefault()

    const formData = new FormData(e.target as HTMLFormElement);
    const body = Object.fromEntries(formData);

    

    try {
      const response = await signIn(body)

      if (response.status === 200) {
      
        console.log(response.data);
        localStorage.setItem('token', response.data.token)
      } else if (response.status === 404) {
        toast("El usuario ingresado no existe", { type: 'error' })
      } 
    } catch (error) {
      console.log(error)
    }
  }

  return (
    <>
      <Head>
        <title> Inicio de sesión </title>
      </Head>
      <ToastContainer />

      <Top title='Inicio de sesión' icon={<LoginNotimed />} />

      <form className='w-full h-full px-4 mt-4 mb-8 space-y-5 md:px-16 items-center flex flex-col justify-center' onSubmit={onSubmit}>
        <InputGroup className="focus:outline-none focus:border-blue-600"
          placeholder="mrroboto@example.com"
          maxLength={256}
          minLength={3}
          required={true}
          label="Ingresa tu correo:"
          type="text"
          icon={<MdMailOutline className="absolute w-9 h-9 pl-3 text-onSurface-variant" />}
          identifier="email" />

        <InputGroup className="focus:outline-none focus:border-blue-600"
          placeholder="Contraseña"
          minLength={3}
          required={true}
          label="Ingresa tu contraseña:"
          type="password"
          icon={<MdLock className="absolute w-9 h-9 pl-3 text-onSurface-variant" />}
          identifier="password" />
        <div className='w-full h-full px-4 mt-4 mb-8 space-y-5 md:px-16 items-center flex flex-col justify-center'>
          <span className='h-auto w-auto mx-2 justify-center items-center text-blue-600 hover:cursor-pointer hover:underline'>
            ¿Olvidaste tu contraseña?
          </span>
          <section className='w-full h-auto flex flex-row space-x-8 items-center max-w-[18.75rem] mx-2 justify-center'>
            <LogInButton text='Inicia sesión' />
          </section>
          <span className='h-auto w-auto hover:underline mx-2'>
            ¿No tienes cuenta?
          </span>
          <div className='w-full h-[1px] bg-outline mx-2' />
          <section className='w-full h-auto flex flex-row space-x-8 items-center max-w-[18.75rem] mx-2 justify-center'>
            <RegisterButton text='Registrate' />
          </section>
        </div>
      </form>
    </>
  )
}

export default Home
