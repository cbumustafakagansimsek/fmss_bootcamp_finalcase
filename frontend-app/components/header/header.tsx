import Link from 'next/link'
import React from 'react'
import { FaRegUser } from "react-icons/fa";

export default function Header() {
  return (
    <div className='w-full h-20 bg-white fixed top-0 left-0 z-10 border-b-2'>
        <div className='flex justify-between h-full w-full container mx-auto p-5'>
            <div className=' flex text-2xl font-semibold'>
            <Link href={"/ilan/sayfa/1"}>İLANLAR</Link>

            </div>
            <div>
                <Link href={"/user/1"} className='flex items-center'><FaRegUser className='mx-2' />mustafa</Link>
                
            </div>
        </div>
    </div>
  )
}
