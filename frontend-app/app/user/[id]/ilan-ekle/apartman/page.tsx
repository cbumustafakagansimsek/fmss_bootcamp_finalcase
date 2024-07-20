import React from 'react'
import FlatAddForm from './flat-add-form';


export default function page({params}:{
    params:{
    id:string
}}) {

  return (
    <div className='py-32 flex justify-center absolute top-0 bg-black bg-opacity-30 w-full'>
        <FlatAddForm id={params.id}></FlatAddForm>
    </div>
  )
}
