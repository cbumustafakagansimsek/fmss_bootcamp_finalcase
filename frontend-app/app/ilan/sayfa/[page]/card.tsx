import React from 'react'
import Image from 'next/image'
import Link from 'next/link'
import { MdOutlineChair } from "react-icons/md";
import { IoBedOutline } from "react-icons/io5";
import { LiaRulerCombinedSolid } from "react-icons/lia";


export default function Card({ad}:any) {
    
  return (
    <Link href={"/ilan/"+ad.id} className='flex flex-col justify-between w-[300px] bg-white rounded-lg overflow-hidden hover:scale-105 duration-100 shadow-lg'>
        <div className='flex flex-col justify-between'>
        <Image
            src={"/home_image.jpeg"}
            width={300} 
            height={250}
            alt="image"
        />
        <div className='p-3 flex flex-col'>
            <div className=' border-b'>
                <h2 className='text-xl font-semibold text-slate-600 capitalize'>{ad.title}</h2>
                <span className='text-slate-400 capitalize'>{ad.province},{ad.district}</span>
            </div>
            <div className='flex gap-2'>
                <span className='flex items-center'><IoBedOutline/>{ad.numberOfRooms}</span>
                <span className='flex items-center'><MdOutlineChair />{ad.numberOfLivingRooms}</span> 
                <span className='flex items-center'><LiaRulerCombinedSolid />{ad.size} m²</span>
            </div>
        </div>
        </div>
        <span className='text-xl p-3 font-semibold'>{ad.amount} ₺</span>

    </Link>
  )
}
