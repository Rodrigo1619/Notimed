import { browser } from "process";
import React, { FC } from "react";
import { TopVector } from "../interfaces/props";
import UserNotimed from "./UserNotimed";

const Top: FC<TopVector> = ({ className, title, icon }) => {

    function topOrLeft() {
        // if ( > 1020) {
        //     return (
        //         <svg width="410" className={className} height="982" viewBox="0 0 410 982" fill="none" xmlns="http://www.w3.org/2000/svg">
        //             <path d="M0 982L0 0H327.787C397.613 203.09 407.158 296.338 410 493.389C406.049 730.261 381.967 835.826 323.879 982H0Z" fill="#2A56C8" />
        //         </svg>
    
        //     )
        // } else {
            return (
                <svg
                    className="h-full mb-8 lg:h-80 lg:w-full"
                    viewBox="0 0 768 316"
                    fill="none"
                    xmlns="http://www.w3.org/2000/svg">
                    <path d="M0 0L768 0V252.635C609.168 306.453 536.241 313.81 382.131 316C196.879 312.955 114.319 294.394 0 249.624L0 0Z" fill="#2A56C8" />
                </svg>
            )
        //}
    }

    return (
        <div className="flex flex-wrap items-center">
            <div className="flex flex-wrap items-center absolute w-full justify-center space-x-4 text-center">
                <span className="w-auto text-onPrimary text-headline4 h-auto"> {title} </span>
                {icon}
            </div>
            {topOrLeft()}
        </div>
    )
}

export default Top;