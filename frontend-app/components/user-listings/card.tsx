"use client"

import React, { FormEvent } from 'react'
import Image from 'next/image'
import { MdOutlineChair } from "react-icons/md";
import { IoBedOutline } from "react-icons/io5";
import { LiaRulerCombinedSolid } from "react-icons/lia";
import Link from 'next/link';

export default function Card({data}:any) {

    async function changeStatus(event: FormEvent<HTMLFormElement>) {
        const formData = new FormData(event.currentTarget)
        
        const response = await fetch(`http://localhost:8080/api/v1/listing?status=${formData.get("status")}&id=1`,{
            method:"PUT"
        })
        
    }
  return (
    <div className='block w-[300px] bg-white rounded-lg overflow-hidden duration-100 shadow-lg'>
        <div className='h-8 w-full'>
            <form onChange={changeStatus} className='flex gap-1'>
                <label htmlFor="">Aktif</label>
                <input type="radio" name='status' value={"ACTIVE"} defaultChecked={data.status === "ACTIVE"}/>
                <label htmlFor="">Pasif</label>
                <input type="radio" name='status' value={"PASSIVE"} defaultChecked={data.status === "PASSIVE"}/>
            </form>
        </div>
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
        <Link href={`/ilan/${data.id}`} className='flex items-center justify-center border-t p-3 text-lg font-semibold hover:bg-slate-200'>İlana Git</Link>

    </div>
  )
}
