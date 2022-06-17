import React, { FC } from "react";
import { MdPersonSearch } from "react-icons/md";

const SearchInput: FC = () => {
    return (
        <div className="flex flex-row w-fill h-[40px] mt-4 mx-4">
            <div className="w-full flex items-center border-[1px] border-outline rounded-full">
                <input
                    className="rounded-l-full pl-3 bg-surface placeholder:text-bodyMedium w-full h-full"
                    inputMode="email"
                    minLength={10}
                    required
                    placeholder="Busca un usuario por email" />
                    <MdPersonSearch size={24} role="button" className="mr-4 text-onSurface" />
            </div>
        </div>
    )
}

export default SearchInput;