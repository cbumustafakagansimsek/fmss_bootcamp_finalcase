import React from 'react'
import Image from 'next/image'


const getListing=async (id:number) => {

    const response = await fetch("http://localhost:8080/api/v1/listing/"+id,{cache:"no-store"});
  
    return await response.json();
  
  }


export default async function page({params}:any) {
    const listing= await getListing(params.id);
    
  return (
    <div className='py-32 container mx-auto px-4'>
        <div className='bg-white p-5 my-5 rounded-lg shadow-lg'>
            <h2 className='border-b text-2xl font-semibold py-3'>Yayınlayan</h2>
            <div className='flex flex-col gap-2'>
                <span className='text-xl font-semibold'>İsim: <span className='font-normal'>mustafa</span></span>
                <span className='text-xl font-semibold'>Soyisim: <span className='font-normal'>mustafa</span></span>
                <span className='text-xl font-semibold'>Yayınlana Tarihi: <span className='font-normal'>mustafa</span></span>
            </div>
            

        </div>
        <div className='bg-white p-5 my-5 rounded-lg shadow-lg'>
            <h1 className='text-2xl font-semibold py-5'>Lorem ipsum dolor sit amet.</h1>
            <div className='relative h-[50vw]'>
            <Image
                src={"/home_image.jpeg"}
                fill={true}
                sizes="(min-width: 808px) 50vw, 100vw"
                objectFit='cover'
                alt="image"
            />
            </div>
            <div className='py-10'>
                <h2 className='border-b text-2xl font-semibold py-3'>Açıklama</h2>
                <p className='text-slate-600 py-3'>{listing.description}</p>
            </div>
            <div className='py-10 flex flex-col gap-2'>
                <h2 className='border-b text-2xl font-semibold py-3'>Özellikler</h2>
                <span className='text-lg font-semibold'>Konut Tipi: <span className='font-normal'>{listing.houseType}</span></span>
                <span className='text-lg font-semibold'>m²: <span className='font-normal'>{listing.size}</span></span>
                <span className='text-lg font-semibold'>Oda Sayısı: <span className='font-normal'>{listing.numberOfRooms}+{listing.numberOfLivingRooms}</span></span>
                <span className='text-lg font-semibold'>Fiyat: <span className='font-normal'>{listing.amount}</span></span>

                {(listing.floorNumber==null)?"":<span className='text-lg font-semibold'>Bulunduğu Kat: <span className='font-normal'>{listing.floorNumber}</span></span>}
                {(listing.yardSize==null)?"":<span className='text-lg font-semibold'>Açık Alan(m²): <span className='font-normal'>{listing.yardSize}</span></span>}
                {(listing.hasPool==null)?"":<span className='text-lg font-semibold'>Havuz: <span className='font-normal'>{listing.hasPool?"var":"yok"}</span></span>}
                
                
                
            </div>
            
        </div>
        
         
       
    </div>
  )
}
