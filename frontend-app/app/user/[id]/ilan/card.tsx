"use client"

import React, { FormEvent, useState } from 'react'
import Image from 'next/image'
import { MdOutlineChair } from "react-icons/md";
import { IoBedOutline } from "react-icons/io5";
import { LiaRulerCombinedSolid } from "react-icons/lia";
import Link from 'next/link';
import { useCookies } from 'next-client-cookies';
import { FaCheck } from 'react-icons/fa';

export default function Card({data}:any) {
  return (
    <div className='block w-[300px] bg-white rounded-lg overflow-hidden duration-100 shadow-lg'>

        <Image
            src={"/home_image.jpeg"}
            width={300} 
            height={250}
            alt="image"
        />
        <div className='p-3 flex flex-col'>
            <div className=' border-b'>
                <h2 className='text-xl font-semibold text-slate-600'>{data.title}</h2>
                <span className='text-slate-400'>{data.province},{data.district}</span>
            </div>
            <div className='flex gap-2'>
                <span className='flex items-center'><IoBedOutline/>{data.numberOfRooms}</span>
                <span className='flex items-center'><MdOutlineChair />{data.numberOfLivingRooms}</span> 
                <span className='flex items-center'><LiaRulerCombinedSolid />{data.size} m²</span>
            </div>

            
            <span className='text-xl py-3 font-semibold'>{data.amount} ₺</span>

        </div>
        <div className='grid grid-cols-2'>
            <Link href={`/user/${data.userId}/ilan/guncelle/${data.id}`} className='border-e flex items-center justify-center border-t p-3 text-lg font-semibold hover:bg-slate-200'>Düzenle</Link>
            <Link href={`/ilan/${data.id}`} className='flex items-center justify-center border-t p-3 text-lg font-semibold hover:bg-slate-200'>İlana Git</Link>
        </div>

    </div>
  )
}
