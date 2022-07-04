import type { NextPage } from 'next'
import Head from 'next/head'
import Image from 'next/image'
import Navbar from '../../src/components/Navbar'
import UserIcon from '../../src/components/svg/UserIcon'
import UserNotimed from '../../src/components/svg/UserNotimed'

const Profile: NextPage = () => {
    return (
        <>
        <Navbar
                    title='Perfil'
                    logo={<UserNotimed className='h-[2.25rem] w-[2.25rem]' />}
                    isEnabled={true}
                    isBack={false}
                />

        <div className='flex flex-col justify-center  
        sm:items-start sm:w-[353px] sm:h-[257px] sm:mt-[100px] 
        md:items-center md:w-[768px] md:h-[257px] md:mt-[220px]
        lg:items-center lg:w-[768px] lg:h-[302px] lg:-mt-1 lg:mx-[310px] 
        p-[32px] gap-[12px]'>
            <UserIcon/>
           <p className='text-center text-[20px] font-semibold w-[155px] h-[23px] top-[94px] left-[306.5px] '>MrRobotoAdmin</p>
           <p className='italic font-normal'>admin@gmail.com</p>
           <div className='w-[171px] h-[66px] top-[159px] left-[298.5px] gap-[12px]'>
            <p className='font-medium'>Fecha de nacimiento</p>
            <button className='w-[171px] h-[36px] top-[30px] rounded-[12px] bg-primaryContainer'>12/12/1972</button>
           </div>

        </div>                
        </>
    )
}

export default Profile
