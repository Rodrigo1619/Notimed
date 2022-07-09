import React, { FC, useState,useEffect, useRef } from "react";
import { MdEdit, MdDelete, MdPerson, MdPersonRemove } from "react-icons/md";
import Dialog from '@mui/material/Dialog';
import DialogActions from '@mui/material/DialogActions';
import DialogContent from '@mui/material/DialogContent';
import DialogContentText from '@mui/material/DialogContentText';
import DialogTitle from '@mui/material/DialogTitle';
import { Divider } from "@mui/material";
import Link from "next/link";


const UserCard: FC = () => {

  const [isOpen, setOpen] = useState(false)

  // Variables de usuario
  const name = "Fernando"
  const email = "fernanortega.sv9292@gmail.com"

  function showDialog() {
    return (
      <Dialog
        open={isOpen}
        onClose={() => setOpen(!isOpen)}
      >
        <DialogTitle className='bg-surface flex flex-col items-center font-sans text-center justify-center max-w-[321px]'>
          <MdPersonRemove size={24} className='text-onSurface-variant' />
          ¿Eliminar usuario?
        </DialogTitle>
        <DialogContent className="bg-surface max-w-[321px]">
          <DialogContentText className="font-sans text-bodyMedium" >
            Esta accion eliminara un usuario. La siguiente cuenta sera eliminada:
          </DialogContentText>
        </DialogContent>
        <Divider className='max-w-[321px]' />
        <DialogContent className='bg-surface flex flex-row space-x-3 max-w-[321px]'>
          <DialogContent className="p-0 flex flex-row space-x-3 items-center">
            <DialogContent className="p-0 max-w-[35px] min-w-[35px] flex justify-center items-center h-[35px] bg-primary rounded-full">
              <MdPerson size={21} className="bg-primary w-[21px] h-[21px] text-onPrimary rounded-full" />
            </DialogContent>
            <DialogContent className="p-0 flex flex-col w-3/4">
              <DialogContentText className='font-sans text-bodyMedium'>
                {name}
              </DialogContentText>
              <DialogContentText className='font-sans text-bodyMedium break-all'>
                {email}
              </DialogContentText>
            </DialogContent>
          </DialogContent>
        </DialogContent>
        <Divider className='max-w-[321px]' />
        <DialogActions className="bg-surface max-w-[321px] p-6 space-x-4">
          <button className='text-primary labelLarge' onClick={() => setOpen(!isOpen)}>
            Cancelar
          </button>
          <button className='text-primary labelLarge' onClick={() => setOpen(!isOpen)} autoFocus>
            Ok
          </button>
        </DialogActions>
      </Dialog>
    );
  }

  return (
    <div className='border-[1px] border-outline text-onSurface 
        rounded-lg w-full h-auto p-4 text-body2 space-y-3'>

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
        <Link href='/users/update'>
          <button className='flex justify-center items-center rounded-full border-[1px] 
            border-outline h-10 w-10 text-button text-onSurface-variant 
            hover:bg-onSurfaceVariantState-hover focus:bg-onSurfaceVariantState-focus'
          >
            <MdEdit size={24} />
          </button>
        </Link>
        <button className='flex justify-center items-center rounded-full border-[1px] 
            border-outline h-10 w-10 text-button text-onSurface-variant
            hover:bg-onSurfaceVariantState-hover focus:bg-onSurfaceVariantState-focus'
          onClick={() => setOpen(!isOpen)}>
          <MdDelete size={24} />
        </button>
      </div>
      {showDialog()}
    </div>
  )
}

export default UserCard;