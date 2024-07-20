import React from 'react'
import VillaAddForm from './villa-add-form';


export default function page({params}:{
    params:{
    id:string
}}) {
    
  return (
    <div className='py-32 flex justify-center absolute top-0 bg-black bg-opacity-30 w-full'>
        <VillaAddForm id={params.id}></VillaAddForm>
    </div>
  )
}