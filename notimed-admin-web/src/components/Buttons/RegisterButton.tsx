import React, { FC } from "react";
import { ButtonProps } from "../interfaces/props";

const RegisterButton: FC<ButtonProps> = ({ className, text, type }) => {
    return (
        <a className="bg-surface border-[1px] border-outline max-w-[18.75rem] labelLarge w-full rounded-full h-10 text-onSurface" type={"button" ?? type}>
            <div className="hover:bg-onSurfaceState-hover focus:bg-onSurfaceState-focus
                px-7 py-2 w-full rounded-full">
                {text}
            </div>
        </a>
    )
}

export default RegisterButton;