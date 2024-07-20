"use client"
import React from 'react'
import DetachedHouseAddForm from './detached-house-add-form';


export default function page({params}:{
    params:{
    id:string
}}) {

  return (
    <div className='py-32 flex justify-center absolute top-0 bg-black bg-opacity-30 w-full'>
        <DetachedHouseAddForm id={params.id}></DetachedHouseAddForm>
    </div>
  )
}