import Link from "next/link";
import React, { FC } from "react";
import { ButtonProps } from "../interfaces/props";

const CancelButton: FC<ButtonProps> = ({ className, text }) => {
    return (
        <Link href="/users">
            <button className="bg-errorContainer text-onErrorContainer w-1/2 rounded-full labelLarge h-10" type="button">
                <div className="hover:bg-onErrorContainerState-hover focus:bg-onErrorContainerState-focus
                px-7 py-2 w-full rounded-full">
                    {text}
                </div>
            </button>
        </Link>
    )
}

export default CancelButton