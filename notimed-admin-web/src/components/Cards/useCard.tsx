import type { NextPage } from 'next'
import Head from 'next/head'
import Image from 'next/image'
import { MdEdit, MdDelete } from "react-icons/md";



const Card: NextPage = () => {
  return (
    <><div className='text-display1 bg-surface text-onSurface   hover:bg-onSurfaceState-hover focus:bg-onSurfaceState-focus'>
      <h1> Login </h1>
    </div>
    <div className='border-2 border-gray-400 text-gray-900 container rounded-lg w-96 box-border h-72 place-items-center text-body2'>
    <div className='grid grid-cols-2 h-14 text-justify mb-0 mx-4'>
        <h2 className='mt-2 text-headline4 '>Nombre</h2>
        <h5 className='mt-4 text-right'>usuario</h5>
      </div>
      <section className='h-44 border-y-2 border-gray-400 mx-4'>
        <div className="mt-4  grid grid-cols-2">
          <h5>Correo electronico:</h5>
          <h5>mrroboto@example.com</h5>
          </div>
          <div className="mt-4 grid grid-cols-2">
          <h5>Edad:</h5>
          <h5>40</h5>
          </div>
          <div className="mt-4 grid grid-cols-2"> 
          <h5>Fecha de nacimiento:</h5>
          <h5>12/12/1982</h5>
          </div>
          <div className="mt-4 grid grid-cols-2">
          <h5>Genero:</h5>
          <h5>Masculino</h5>
          </div>
          
      </section>
      <div className=' text-right grid-cols-2 mt-2 h-10 mx-4'>
      <button className='mr-4 ring-2 ring-gray-400 rounded-full h-10 w-10 text-button'>
      <MdEdit className='container' size="2em" />
        </button>
      <button className='mr-0 ring-2 ring-gray-400 rounded-full h-10 w-10 text-button'>
       <MdDelete className='container' size="2em" />
        </button>
      </div>
    </div>
      </>
  )
}

export default Card