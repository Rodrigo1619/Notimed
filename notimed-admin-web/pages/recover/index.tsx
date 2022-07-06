import type { NextPage } from 'next'
import Head from 'next/head'
import { MdMailOutline } from "react-icons/md";
import InputGroup from '../../src/components/Inputs/InputGroup';
import RecoverNotimed from '../../src/components/svg/RecoverNotimed';
import Top from '../../src/components/svg/Top';

const Recover: NextPage = () => {

    const onSubmit = async (e: React.FormEvent) => {
        e.preventDefault()
        
    }
    return (
        <>
            <Head>
                <title> Recuperar contraseña </title>
            </Head>
            <Top title='Recuperar contraseña' icon={<RecoverNotimed />} className="h-[10rem]"/>
            <form className='w-full h-full px-4 mt-4 mb-8 space-y-5 md:px-16 items-center flex flex-col justify-center' onSubmit={onSubmit}>
                <InputGroup className="focus:outline-none focus:border-blue-600"
                    placeholder="mrroboto@example.com"
                    minLength={4}
                    required={true}
                    label="Ingresa tu correo:"
                    type="text"
                    icon={<MdMailOutline className="absolute w-9 h-9 pl-3 text-onSurface-variant" />}
                    identifier="emaildAdminRegister" />
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
