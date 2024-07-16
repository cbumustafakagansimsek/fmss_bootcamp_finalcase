import React from 'react'
import AddListingNavigation from '@/components/add-listing-navigation/navigation';
import Link from 'next/link';
import { FaCheck } from "react-icons/fa";
import { ImCross } from "react-icons/im";
import { headers } from "next/headers";


export default function UserPanel({id}:any) {
    

  return (
    <div className='container mx-auto px-4 py-20'>
    <div className='bg-white p-5 my-5 rounded-lg'>
        <h2 className='border-b text-2xl font-semibold py-3'>Kullanıcı</h2>
        <div className='flex flex-col gap-2'>
            <span className='text-xl font-semibold'>İsim: <span className='font-normal'>mustafa</span></span>
            <span className='text-xl font-semibold'>Soyisim: <span className='font-normal'>mustafa</span></span>
            <span className='text-xl font-semibold'>email: <span className='font-normal'>mustafa</span></span>
            <span className='text-xl font-semibold'>Abonelik: <span className=''><FaCheck className='text-green-600' /><ImCross className='text-red-600' /></span></span>
        </div>
    </div>
    <div className='flex flex-wrap gap-5 justify-center bg-white rounded-lg p-5'>
        <Link href={"/user/1/payment/1"} className='bg-white text-black rounded-lg min-h-[250px] min-w-[300px] w-[40%] flex justify-center items-center flex-col border-2 border-black hover:bg-slate-200'>
            <h2 className='text-2xl font-semibold'>1 Aylık Abonelik</h2>
            <span>Aylık 10 İlan Yayınlama Hakkı</span>
        </Link>

        <Link href={"/user/1/payment/10"} className='bg-white text-black rounded-lg min-h-[250px] min-w-[300px] w-[40%] flex justify-center items-center flex-col border-2 border-black hover:bg-slate-200 '>
            <h2 className='text-2xl font-semibold'>10 Aylık Abonelik</h2>
            <span>Aylık 10 İlan Yayınlama Hakkı</span>
        </Link>
    </div>
    <div className='bg-white rounded-lg p-5 my-5'>
    <h1 className='text-2xl font-semibold text-black text-center py-2'>İlan Kaydet</h1>
        <AddListingNavigation id={id}></AddListingNavigation>
    </div>


</div>
  )
}
