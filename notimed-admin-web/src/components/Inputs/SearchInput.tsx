import React, { FC } from "react";
import { MdPersonSearch } from "react-icons/md";

const SearchInput: FC = () => {
    return (
        <div className="flex flex-row w-full h-12 mt-4">
            <div className="w-full flex items-center border-[1px] border-outline rounded-2xl">
                <input
                    className="rounded-l-2xl pl-3 bg-surface placeholder:text-bodyMedium w-5/6 lg:w-full h-full"
                    inputMode="email"
                    minLength={10}
                    placeholder="Busca un usuario por email" />
                    <MdPersonSearch size={24} role="button" className="rounded-r-2xl w-1/6 lg:w-1/12 px-3 py-2 h-full 
                    text-onSurface hover:bg-onSurfaceState-hover focus:bg-onSurfaceState-focus" />
            </div>
        </div>
    )
}

export default SearchInput;