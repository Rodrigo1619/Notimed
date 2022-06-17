import type { NextPage } from 'next';
import Navbar from '../../src/components/Navbar';
import UserNotimed from '../../src/components/svg/UserNotimed';
import SearchInput from '../../src/components/Inputs/SearchInput';
import UserCard from '../../src/components/Cards/UserCard';
import { MdPersonAdd } from 'react-icons/md';
import Head from 'next/head';

const Users: NextPage = () => {
    return (
        <>
            <Head>
                <title> Usuarios </title>
            </Head>
            <div className='w-full h-screen'>
                <Navbar
                    title='Usuarios'
                    logo={<UserNotimed className='h-[2.25rem] w-[2.25rem]' />}
                    isEnabled={true}
                />
                <div className="w-full h-full px-4 mt-4 space-y-4 md:px-16">
                    <div className='space-y-4 md:flex md:flex-row md:items-center md:space-x-4'>
                        <SearchInput />
                        <button className="w-full h-10 bg-primary rounded-2xl 
                            flex flex-row justify-center items-center text-onPrimary
                            space-x-4 md:w-3/6">
                            <MdPersonAdd size={24} />
                            <span className='labelLarge'> Nuevo usuario </span>
                        </button>
                    </div>
                    <UserCard />
                    <UserCard />
                    <UserCard />
                    <UserCard />
                    <UserCard />
                    <UserCard />
                </div>
            </div>
        </>
    )
}

export default Users
