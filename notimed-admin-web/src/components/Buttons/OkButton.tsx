import React, { FC } from "react";
import { ButtonProps } from "../interfaces/props";

const OkButton: FC<ButtonProps> = ({ className, text }) => {
    return (
        <button className="bg-primaryContainer text-onPrimaryContainer labelLarge w-1/2 rounded-full" type="button">
            <div className="hover:bg-onPrimaryContainerState-hover focus:bg-onPrimaryContainerState-focus
                px-7 py-2 w-full rounded-full">
                {text}
            </div>
        </button>
    )
}

export default OkButton;