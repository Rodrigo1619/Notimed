import React, { FC } from "react";
import { TopVector } from "../interfaces/props";
import UserNotimed from "./UserNotimed";

const Top: FC<TopVector> = ({ className, title, icon }) => {
    return (
        <div className={`w-full py-14 flex flex-wrap items-center bg-primary mb-8 md:py-20 lg:mb-14 ${className}`}>
            <div className="flex flex-wrap items-center absolute w-full justify-center space-x-4 text-center">
                <span className="w-auto text-onPrimary text-headline4 h-auto"> {title} </span>
                {icon}
            </div>
        </div>
    )
}

export default Top;