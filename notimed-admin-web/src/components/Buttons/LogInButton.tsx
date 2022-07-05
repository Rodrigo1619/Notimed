import React, { FC } from "react";
import { ButtonProps } from "../interfaces/props";

const LogInButton: FC<ButtonProps> = ({ className, text, type }) => {
    return (
        <button className="bg-primary text-onPrimary labelLarge w-full rounded-full h-10 max-w-[18.75rem]">
            <div className="hover:bg-onPrimaryState-hover focus:bg-onPrimaryState-focus
                px-7 py-2 w-full rounded-full">
                {text}
            </div>
        </button>
    )
}

export default LogInButton;