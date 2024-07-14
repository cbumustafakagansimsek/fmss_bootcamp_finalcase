import Link from 'next/link';
import React from 'react'
import { FaCheck } from "react-icons/fa";
import { ImCross } from "react-icons/im";

export default function page() {
  return (
    <div className='container mx-auto px-4 py-20'>
        <div className='bg-white p-5 my-5 rounded-lg'>
            <h2 className='border-b text-2xl font-semibold py-3'>Yayınlayan</h2>
            <div className='flex flex-col gap-2'>
                <span className='text-xl font-semibold'>İsim: <span className='font-normal'>mustafa</span></span>
                <span className='text-xl font-semibold'>Soyisim: <span className='font-normal'>mustafa</span></span>
                <span className='text-xl font-semibold'>email: <span className='font-normal'>mustafa</span></span>
                <span className='text-xl font-semibold'>Abonelik: <span className=''><FaCheck className='text-green-600' /><ImCross className='text-red-600' /></span></span>
            </div>
        </div>
        <div className='flex flex-wrap gap-5 justify-center bg-white rounded-lg p-5'>
            <Link href={"/user/1/payment/1"} className='bg-green-600 text-white rounded-lg min-h-[250px] min-w-[300px] w-[40%] flex justify-center items-center flex-col'>
                <h2 className='text-2xl font-semibold'>1 Aylık Abonelik</h2>
                <span>Aylık 10 İlan Yayınlama Hakkı</span>
            </Link>

            <Link href={"/user/1/payment/10"} className='bg-green-600 text-white rounded-lg min-h-[250px] min-w-[300px] w-[40%] flex justify-center items-center flex-col'>
                <h2 className='text-2xl font-semibold'>10 Aylık Abonelik</h2>
                <span>Aylık 10 İlan Yayınlama Hakkı</span>
            </Link>
        </div>
    </div>
    
  )
}
