import type { NextPage } from 'next';
import Navbar from '../../src/components/Navbar';
import UserNotimed from '../../src/components/svg/UserNotimed';
import SearchInput from '../../src/components/Inputs/SearchInput';
import UserCard from '../../src/components/Cards/UserCard';
import { MdPersonAdd } from 'react-icons/md';
import Head from 'next/head';
import ReactPaginate from 'react-paginate';
import Paginate from '../../src/components/svg/Paginate';
import { useState } from 'react';

const Users: NextPage = () => {

    const items = [1, 2, 3, 4, 5, 6, 7, 8, 9]

    const [pageCount, setPageCount] = useState(items.length)



    return (
        <>
            <Head>
                <title> Usuarios </title>
            </Head>
            <div className='w-full'>
                <Navbar
                    title='Usuarios'
                    logo={<UserNotimed className='h-[2.25rem] w-[2.25rem]' />}
                    isEnabled={true}
                />
                <div className="w-full h-full px-4 mb-8 space-y-4 md:px-16">
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

                    <ReactPaginate
                        className='bg-primary flex flex-row 
                        w-full justify-between items-center labelSmall text-onPrimary rounded-xl px-3 py-3 space-x-1 md:text-titleSmall'
                        breakLabel="..."
                        marginPagesDisplayed={2}
                        pageRangeDisplayed={1}
                        nextLabel={<Paginate className='rounded-full p-1 h-auto w-auto rotate-180 
                                    hover:bg-onPrimaryState-hover focus:bg-onPrimaryContainerState-focus md:p-2' />}
                        previousLabel={<Paginate className='rounded-full p-1 h-auto w-auto 
                                    hover:bg-onPrimaryState-hover focus:bg-onPrimaryContainerState-focus md:p-2' />}
                        pageCount={pageCount}
                        pageClassName="w-3 h-3 p-3 flex items-center justify-center"
                        activeClassName='w-3 h-3 p-3 flex items-center justify-center 
                            bg-primaryContainer text-onPrimaryContainer rounded-lg'
                    />
                </div>
            </div>
        </>
    )
}

export default Users
