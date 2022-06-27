import React, { FC } from "react";
import { ButtonProps } from "../interfaces/props";

const RegisterButton: FC<ButtonProps> = ({ className, text }) => {
    return (
        <button className="bg-surface border-[1px] border-outline labelLarge w-full rounded-full h-10 text-onSurface" type="submit">
            <div className="hover:bg-onSurfaceState-hover focus:bg-onSurfaceState-focus
                px-7 py-2 w-full rounded-full">
                {text}
            </div>
        </button>
    )
}

export default RegisterButton;