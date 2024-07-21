"use client"
import React, { FormEvent, useState } from 'react'
import { useCookies } from 'next-client-cookies';
import { useRouter } from 'next/navigation';
import Link from 'next/link';
import { ImCross } from 'react-icons/im';


export default function VillaAddForm({id}:{id:string}) {
    const cookies = useCookies();
    const router = useRouter();
    const [error,setError] = useState("");
    

    async function createAd(event: FormEvent<HTMLFormElement>) {
        event.preventDefault();
        const formData = new FormData(event.currentTarget)

        const request ={
            title: formData.get("title"),
            description: formData.get("description"),
            amount: formData.get("amount"),
            province: formData.get("province"),
            district: formData.get("district"),
            size: formData.get("size"),
            numberOfRooms: formData.get("numberOfRooms"),
            numberOfLivingRooms: formData.get("numberOfLivingRooms"),
            numberOfFloors:formData.get("numberOfFloors"),
            yardSize: formData.get("yardSize"),
            hasPool: formData.get("hasPool")

        }
        
        
        const response = await fetch("http://localhost:8080/api/v1/ads/secure/villa",{
            method:"POST",
            headers:{
                Authorization:`Bearer ${cookies.get("token")}`,
                'Content-Type': 'application/json',      
            },
            body:JSON.stringify(request)
        })
        
        if(response.status==200){
            router.push(`/user/${id}/ilan`);
        }else{
            const data = await response.json();
            setError(data.message)
        }
        
        
    }
  return (
        <form onSubmit={createAd} className='flex flex-col gap-5 bg-white rounded-lg p-5 shadow-lg'>
            <Link href={`/user/${id}/ilan`} className='flex justify-end'><ImCross className='text-red-600' /></Link>
            <h1 className='text-lg font-semibold text-black'>Villa Kaydet</h1>

            <div className='flex flex-col'>
                <label htmlFor="">Başlık</label>
                <input type="text" name='title' className='block border-2 rounded-lg p-2' required/>
            </div>
            <div className='flex flex-col'>
                <label htmlFor="">Açıklama</label>
                <textarea name="description" rows={4} cols={40} maxLength={255} className='block border-2 rounded-lg p-2'/>
            </div>
            <div className='flex flex-col'>
                <label htmlFor="">İl</label>
                <input type="text" name='province' className='block border-2 rounded-lg p-2' required/>
            </div>
            <div className='flex flex-col'>
                <label htmlFor="">İlçe</label>
                <input type="text" name='district' className='block border-2 rounded-lg p-2' required/>
            </div>
            <div className='flex flex-col'>
                <label htmlFor="">m²</label>
                <input type="number" name='size' className='block border-2 rounded-lg p-2' required/>
            </div>
            <div className='flex flex-col'>
                <label htmlFor="">Oda Sayısı</label>
                <input type="number" name='numberOfRooms' className='block border-2 rounded-lg p-2' required/>
            </div>
            <div className='flex flex-col'>
                <label htmlFor="">Salon Sayısı</label>
                <input type="number" name='numberOfLivingRooms' className='block border-2 rounded-lg p-2' required/>
            </div>
            <div className='flex flex-col'>
                <label htmlFor="">Kat Sayısı</label>
                <input type="number" name='numberOfFloors' className='block border-2 rounded-lg p-2' required/>
            </div>
            <div className='flex flex-col'>
                <label htmlFor="">Açık Alan</label>
                <input type="number" name='yardSize' className='block border-2 rounded-lg p-2' required/>
            </div>
            <div className='flex flex-col'>
                <label htmlFor="">Havuz</label>
                <span className='flex'>var<input type="radio" name='hasPool' className='block border-2 rounded-lg p-2 h-6 w-6' value={"true"} /></span>
                <span className='flex'>yok<input type="radio" name='hasPool' className='block border-2 rounded-lg p-2 h-6 w-6' value={"false"} /></span>
            </div>
            <div className='flex flex-col'>
                <label htmlFor="">Fiyat</label>
                <input type="number" name='amount' className='block border-2 rounded-lg p-2' required/>
            </div>
            <button type='submit' className='flex justify-center items-center bg-black text-white text-xl rounded-lg border-2 border-black hover:bg-white hover:text-black font-semibold p-4'>Kaydet</button>
            <span className='text-red-600 uppercase'>{error}</span>
        </form>
  )
}