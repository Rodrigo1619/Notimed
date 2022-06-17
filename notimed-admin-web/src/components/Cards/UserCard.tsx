import React, { FC } from "react";
import { MdEdit, MdDelete } from "react-icons/md";



const UserCard: FC = () => {
  return (
      <div className='border-[1px] border-outline text-onSurface rounded-lg w-full h-auto p-4 text-body2 space-y-3'>

        <div className='flex flex-row justify-between h-auto items-center'>
          <span className='text-titleLarge'>Nombre</span>
          <span className='text-bodyMedium'>usuario</span>
        </div>

        <section className='h-auto border-y-[1px] border-outline space-y-3 pb-3'>
          <div className="flex flex-row justify-between mt-3">
            <span>Correo electronico:</span>
            <span className="break-all">mrroboto@example.com</span>
          </div>
          <div className="flex flex-row justify-between">
            <span>Edad:</span>
            <span>40</span>
          </div>
          <div className="flex flex-row justify-between">
            <span>Fecha de nacimiento:</span>
            <span>12/12/1982</span>
          </div>
          <div className="flex flex-row justify-between">
            <span>Genero:</span>
            <span>Masculino</span>
          </div>
        </section>
        <div className='flex flex-row w-full justify-end space-x-2 h-auto'>
          <button className='flex justify-center items-center rounded-full border-[1px] 
            border-outline h-10 w-10 text-button text-onSurface-variant'>
            <MdEdit size={24} />
          </button>
          <button className='flex justify-center items-center rounded-full border-[1px] 
            border-outline h-10 w-10 text-button text-onSurface-variant'>
            <MdDelete size={24} />
          </button>
        </div>
      </div>
  )
}

export default UserCard;