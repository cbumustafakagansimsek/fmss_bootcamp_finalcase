import React from 'react'
import Image from 'next/image'
import Link from 'next/link'
import { MdOutlineChair } from "react-icons/md";
import { IoBedOutline } from "react-icons/io5";
import { LiaRulerCombinedSolid } from "react-icons/lia";


export default function Card() {
  return (
    <Link href={"/ilan/1"} className='block w-[300px] bg-white rounded-lg overflow-hidden hover:scale-105 duration-100 shadow-lg'>
        <Image
            src={"/home_image.jpeg"}
            width={300} 
            height={250}
            alt="image"
        />
        <div className='p-3 flex flex-col'>
            <div className=' border-b'>
                <h2 className='text-xl font-semibold text-slate-600'>name</h2>
                <span className='text-slate-400'>İstanbul,avcılar</span>
            </div>
            <div className='flex gap-2'>
                <span className='flex items-center'><IoBedOutline/>5</span>
                <span className='flex items-center'><MdOutlineChair />1</span> 
                <span className='flex items-center'><LiaRulerCombinedSolid />80 m²</span>
            </div>

            
            <span className='text-xl py-3 font-semibold'>140000TL</span>

        </div>
    </Link>
  )
}
