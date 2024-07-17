import { notFound } from 'next/navigation';
import React from 'react'
const getUser=async (id:string) => {
    try{
    const response = await fetch("http://localhost:8080/api/v1/user/"+id,{cache:"default"});
    if(!response.ok){
        throw new Error("Not Found");
    }
    
    return await response.json();
    }
    catch(error){
        console.log(error);
        notFound();
    }
  }
export default async function Publisher({id}:{id:string}) {
    const data =  await getUser(id);
    
  return (
    <div className='bg-white p-5 my-5 rounded-lg shadow-lg'>
        <h2 className='border-b text-2xl font-semibold py-3'>Yayınlayan</h2>
        <div className='flex flex-col gap-2'>
            <span className='text-xl font-semibold'>İsim: <span className='font-normal'>{data.name}</span></span>
            <span className='text-xl font-semibold'>Soyisim: <span className='font-normal'>{data.surname}</span></span>
        </div>
    </div>
  )
}
