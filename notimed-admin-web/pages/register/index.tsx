import type { NextPage } from 'next'
import Head from 'next/head'
import Link from 'next/link';
import React from 'react';
import { MdMailOutline, MdLock, MdPerson, MdCalendarToday } from "react-icons/md";
import InputGroup from '../../src/components/Inputs/InputGroup';
import RegisterNotimed from '../../src/components/svg/RegisterNotimed';
import Top from '../../src/components/svg/Top';

const Register: NextPage = () => {

    const onSubmit = async (e: React.FormEvent) => {
        e.preventDefault()
    }

    return (
        <>
            <Head>
                <title> Registrate </title>
            </Head>
            <Top title="Registrate" icon={<RegisterNotimed />} />
            <form className='w-full h-full px-4 mt-4 mb-8 space-y-5 md:px-16 items-center flex flex-col justify-center' onSubmit={onSubmit}>
                <InputGroup className="focus:outline-none focus:border-blue-600"
                    placeholder="Mr Roboto"
                    minLength={3}
                    required={true}
                    label="Ingresa tu nombre:"
                    type="text"
                    icon={<MdPerson className="absolute w-9 h-9 pl-3 text-onSurface-variant" />}
                    identifier="nameAdminRegister" />

                <InputGroup className="focus:outline-none focus:border-blue-600"
                    placeholder="mrroboto@example.com"
                    minLength={4}
                    required={true}
                    label="Ingresa tu correo:"
                    type="email"
                    icon={<MdMailOutline className="absolute w-9 h-9 pl-3 text-onSurface-variant" />}
                    identifier="emaildAdminRegister" />

                <InputGroup className="focus:outline-none focus:border-blue-600"
                    placeholder="dd/mm/yyyy"
                    required={true}
                    label="Ingresa tu fecha de nacimiento:"
                    type="date"
                    icon={<MdCalendarToday className="absolute w-9 h-9 pl-3 text-onSurface-variant" />}
                    identifier="birthdayAdminRegister" />

                <InputGroup className="focus:outline-none focus:border-blue-600"
                    placeholder="Contraseña"
                    minLength={8}
                    required={true}
                    label="Ingresa tu contraseña:"
                    type="password"
                    icon={<MdLock className="absolute w-9 h-9 pl-3 text-onSurface-variant" />}
                    identifier="passwordAdminRegister" />

                <section className='w-full max-w-[18.75rem]'>
                    <label className='font-sans text-bodyLarge text-onSurface'> Género </label>
                    <div className='mt-2 flex items-center justify-evenly mx-3' id='genderSelection'>
                        <input placeholder='Masculino' type='radio' name='gender' id='genderMale' />
                        <label htmlFor="genderMale"> Masculino </label>
                        <input placeholder='Femenino' type='radio' name='gender' id='genderFemale' />
                        <label htmlFor="genderFemale"> Femenino </label>
                    </div>
                </section>
                <section className='w-full h-auto flex flex-wrap space-x-2 items-center max-w-[18.75rem] mx-2 justify-center'>
                    <span className=''>¿Ya tienes una cuenta?</span>
                    <Link href={'/'}>
                        <a className='text-blue-600 hover:cursor-pointer hover:underline'>Inicia sesión</a>
                    </Link>
                </section>
                <button
                    className="bg-surface border-[1px] border-outline max-w-[18.75rem] labelLarge w-full rounded-full h-10 text-onSurface"
                    type="submit">
                    <div
                        className="hover:bg-onSurfaceState-hover focus:bg-onSurfaceState-focus
                    px-7 py-2 w-full rounded-full">
                        Registrate
                    </div>
                </button>
            </form>
        </>
    )
}

export default Register
