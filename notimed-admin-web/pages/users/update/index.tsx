import type { NextPage } from 'next'
import Head from 'next/head'
import { MdLockOutline, MdOutlineCalendarToday, MdOutlineMailOutline, MdPerson } from 'react-icons/md'
import CancelButton from '../../../src/components/Buttons/CancelButton'
import OkButton from '../../../src/components/Buttons/OkButton'
import InputGroup from '../../../src/components/Inputs/InputGroup'
import Navbar from '../../../src/components/Navbar'
import UserNotimed from '../../../src/components/svg/UserNotimed'

const Update: NextPage = () => {


    return (
        <>
            <Head>
                <title> Actualizando usuario </title>
            </Head>

            <Navbar title={'Actualizando usuario'}
                logo={<UserNotimed className='h-[2.25rem] w-[2.25rem]' />}
                isEnabled={false}
                isBack={true} />

            <form className='w-full h-full px-4 mt-4 mb-8 space-y-5 md:px-16 items-center flex flex-col justify-center'>
                <InputGroup icon={<MdPerson className="absolute w-9 h-9 pl-3 text-onSurface-variant" />}
                    identifier="name"
                    placeholder={'MrRoboto'} type={'text'} minLength={3} required={false} label={'Nombre'} />
                <InputGroup icon={<MdPerson className="absolute w-9 h-9 pl-3 text-onSurface-variant" />}
                    identifier="lastName"
                    placeholder={''} type={'text'} minLength={3} required={false} label={'Apellido'} />

                <InputGroup icon={<MdOutlineMailOutline className="absolute w-9 h-9 pl-3 text-onSurface-variant" />}
                    placeholder='mrroboto@gmail.com'
                    identifier='email' type='email' minLength={5} required={false} label="Correo electronico" />

                <InputGroup icon={<MdOutlineCalendarToday className="absolute w-9 h-9 pl-3 text-onSurface-variant" />}
                    placeholder='mrroboto@gmail.com'
                    identifier='birthday' type='date' required={false} label="Fecha de nacimiento" />

                <InputGroup icon={<MdLockOutline className="absolute w-9 h-9 pl-3 text-onSurface-variant" />}
                    placeholder=''
                    identifier='password' type='password' required={false} label="Contrase??a (temporal)" />

                <section className='w-full h-auto flex flex-row space-x-8 items-center max-w-[18.75rem]'>
                    <OkButton text="Actualizar" />
                    <CancelButton text="Cancelar" />
                </section>
            </form>
        </>
    )
}

export default Update
