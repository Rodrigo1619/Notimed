import React, { FC, useState } from "react";
import Link from "next/link";
import { MdMenu, MdMenuOpen, MdOutlineTableChart, MdGroup, MdPerson, MdLogout } from 'react-icons/md'
import { NavbarProps } from "../interfaces/props";

const Navbar: FC<NavbarProps> = ({ title, logo, isEnabled }) => {

    const [isActive, setIsActive] = useState(false);
    const [isOn] = useState(isEnabled)

    function openNavbar() {
        return (
            <div className="transition-all fixed top-0 left-0 bg-onSurfaceState-focus h-full w-full">
                <div className="surface1 w-10/12 max-w-xs px-3 py-8 rounded-r-2xl h-full  overflow-y-auto flex flex-col justify-between">
                    <div className="flex flex-col">
                        <button
                            className="flex flex-row w-full rounded-full space-x-3 py-4 items-center pl-4
                            hover:bg-onSurfaceState-hover focus:bg-onSurfaceState-focus"
                            onClick={() => { setIsActive(!isActive) }}
                        >
                            <MdMenuOpen size={24} />
                            <span className="text-titleMedium"> Navegación </span>
                        </button>
                        <section className="flex flex-row w-full py-4 pl-4">
                            <span className="text-bodySmall"> Acciones principales </span>
                        </section>
                        <Link href="/dashboard">
                            <a
                                className="flex flex-row w-full  space-x-3 items-center rounded-full
                            pl-4 pr-6 py-4 hover:bg-onSurfaceState-hover focus:bg-onSurfaceState-focus">
                                <MdOutlineTableChart size={24} role="button" />
                                <span className="labelLarge"> Dashboard </span>
                            </a>
                        </Link>
                        <Link href="/users">
                            <a className={`${isOn ? 'bg-secondaryContainer' : ''} flex flex-row w-full space-x-3 items-center rounded-full
                            pl-4 pr-6 py-4 hover:bg-onSurfaceState-hover focus:bg-onSurfaceState-focus`}>
                                <MdGroup size={24} role="button" />
                                <span className="labelLarge"> Usuarios </span>
                            </a>
                        </Link>
                        <Link href="/profile">
                            <a className="flex flex-row w-full space-x-3 items-center rounded-full
                            pl-4 pr-6 py-4 hover:bg-onSurfaceState-hover focus:bg-onSurfaceState-focus">
                                <MdPerson size={24} role="button" />
                                <span className="labelLarge"> Perfil </span>
                            </a>
                        </Link>
                    </div>
                    <div className="border-t-[1px] border-outline mt-3">
                        <Link href="/">
                            <a className="flex flex-row w-full bg-error text-onError rounded-full mt-4">
                                <div className="w-full h-full flex flex-row bg-error px-6 py-4 rounded-full
                                justify-center space-x-3 items-center hover:bg-onErrorState-hover focus:bg-onErrorState-focus">
                                    <MdLogout size={24} role="button" />
                                    <span className="labelLarge "> Cerrar sesión </span>
                                </div>
                            </a>
                        </Link>
                    </div>
                </div>
            </div>
        )
    }

    function normalNavbar() {
        return (
            <div className="transition-all flex flex-row justify-between items-center px-4 py-4 bg-surface md:flex-col md:px-16 lg:px-16 ">
                <div className=" flex md:w-full md:flex-row md:justify-between md:items-center md:py-6">
                    <MdMenu role="button" size={24} 
                        className="w-fit h-fit rounded-full p-1 hover:bg-onSurfaceState-hover focus:bg-onSurfaceState-focus" 
                        onClick={() => setIsActive(!isActive)} />
                    <div className="hidden md:block">
                        {logo}
                    </div>
                </div>
                <span className="text-titleLarge md:w-full md:text-left  lg:mt-0"> {title} </span>
                <div className="md:hidden">
                    {logo}
                </div>
            </div>
        )
    }


    return (
        <>
            {
                isActive ? openNavbar() : normalNavbar()
            }
        </>
    );
}

export default Navbar;