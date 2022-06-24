import React, { FC } from "react";
import { ButtonProps } from "../interfaces/props";

const RegisterButton: FC<ButtonProps> = ({ className, text }) => {
    return (
        <button className="bg-white border-2 border-onPrimaryContainer text-onPrimaryContainer labelLarge w-1/2 rounded-full" type="submit">
            <div className="hover:bg-primaryContainer focus:bg-onPrimaryContainerState-focus
                px-7 py-2 w-full rounded-full">
                {text}
            </div>
        </button>
    )
}

export default RegisterButton;