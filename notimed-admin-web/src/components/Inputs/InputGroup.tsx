import React, { FC, useId } from "react";
import { Md7KPlus } from "react-icons/md";
import { InputProps } from "../interfaces/props";

const InputGroup: FC<InputProps> = ({
    onChange,
    name,
    value,
    className,
    placeholder,
    maxLength,
    minLength,
    required,
    label,
    type, 
    icon,
    identifier,
    
}) => {
    return (
        <section className="space-y-4 w-full max-w-[18.75rem]">
            <label
                htmlFor={identifier}
                className="font-sans text-bodyLarge text-onSurface">
                {label}
            </label>
            <div className="w-full h-fit flex flex-row items-center max-w-[18.75rem]">
                {icon}
                <input onChange={onChange} value={value}
                id={identifier}
                className={`placeholder:text-onSurface-variant text-bodyMedium h-14 w-full
                    bg-surface border-2 border-outline rounded-md max-w-[18.75rem]
                    focus:border-2 focus:border-primary
                    pl-12 pr-4 py-[10px]
                    ${className}`}
                placeholder={placeholder}
                name={name}
                maxLength={maxLength ?? 128}
                minLength={minLength ?? 0}
                required={required ?? false}
                type={type}
                min="1920-01-01"
                max="2018-12-31"
            />
            </div>
        </section>
    );
}

export default InputGroup;