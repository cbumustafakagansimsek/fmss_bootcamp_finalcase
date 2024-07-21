import React from 'react'
import AddAdNavigation from '@/components/add-ad-navigation/navigation';
import Link from 'next/link';
import { FaCheck } from "react-icons/fa";
import { ImCross } from "react-icons/im";
import { headers } from "next/headers";

const getUser =async (id:string) => {
    const response = await fetch(`http://localhost:8080/api/v1/user//${id}` 
    ,{
      cache:'no-store',
      method:'GET',
    }
    );
    console.log("girdi");
    
   return await response.json();
}
export default async function UserPanel({id}:any) {
    const data = await getUser(id);
    console.log(data);
    
  return (
    <div className='container mx-auto px-4'>
    <div className='bg-white p-5 my-5 rounded-lg shadow-lg'>
        <h2 className='border-b text-2xl font-semibold py-3'>Kullanıcı</h2>
        <div className='flex flex-col gap-2'>
            <span className='text-xl font-semibold'>İsim: <span className='font-normal'>{data.name}</span></span>
            <span className='text-xl font-semibold'>Soyisim: <span className='font-normal'>{data.surname}</span></span>
            <span className='text-xl font-semibold'>email: <span className='font-normal'>{data.mail}</span></span>
            <span className='text-xl font-semibold flex'>Abonelik: <span className='px-2'>{data.role=="SUBSCRIPTED"?<FaCheck className='text-green-600' />:<ImCross className='text-red-600' />}</span></span>
        </div>
    </div>
    <div className='flex flex-wrap gap-5 justify-center bg-white rounded-lg p-5 shadow-lg'>
        <Link href={`/user/${id}/payment/1`} className='bg-white text-black rounded-lg min-h-[250px] min-w-[300px] w-[40%] flex justify-center items-center flex-col border-2 border-black hover:bg-slate-200'>
            <h2 className='text-2xl font-semibold'>1 Aylık Abonelik</h2>
            <span>Aylık 10 İlan Yayınlama Hakkı</span>
        </Link>

        <Link href={`/user/${id}/payment/10`} className='bg-white text-black rounded-lg min-h-[250px] min-w-[300px] w-[40%] flex justify-center items-center flex-col border-2 border-black hover:bg-slate-200 '>
            <h2 className='text-2xl font-semibold'>10 Aylık Abonelik</h2>
            <span>Aylık 10 İlan Yayınlama Hakkı</span>
        </Link>
    </div>
    <div className='bg-white rounded-lg p-5 my-5 shadow-lg'>
    <h1 className='text-2xl font-semibold text-black text-center py-2'>İlan Kaydet</h1>
        <AddAdNavigation id={id}></AddAdNavigation>
    </div>


</div>
  )
}
