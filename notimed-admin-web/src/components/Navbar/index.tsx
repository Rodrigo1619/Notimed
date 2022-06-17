import React, { FC, useState } from "react";
import { MdMenu } from 'react-icons/md'
import { NavbarProps } from "../interfaces/props";

const Navbar: FC<NavbarProps> = ({ title, logo }) => {

    const [isActive, setIsActive] = useState(false);

    return (
        <>
            <div className="flex flex-row justify-between items-center px-4 py-4 bg-surface">
                <MdMenu role="button" size={24} className="w-[24px] h-[24px]" />
                <span className="text-titleLarge"> {title} </span>
                {logo}
            </div>
        </>
    );
}

export default Navbar;