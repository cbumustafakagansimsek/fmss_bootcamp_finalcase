import React from 'react'
import Image from 'next/image'
const getAd=async (id:string) => {
    try{
        const response = await fetch("http://localhost:8080/api/v1/ads/"+id,{cache:"default"});
        if(!response.ok){
            throw new Error("Not Found");
        }
        return await response.json();
    }catch(error){
        console.log(error );
    }finally{
        return [];
    }
    
  
  }


export default async function Details({id}:{id:string}) {
    const ad= await getAd(id);
    
  return (
    <div className='bg-white p-5 my-5 rounded-lg shadow-lg'>
            <span className='text-lg font-semibold'>yayınlanma tarihi: <span className='font-normal'>{ad.postedDate}</span></span>
            <h1 className='text-2xl font-semibold py-5'>{ad.title}</h1>
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
                <p className='text-slate-600 py-3'>{ad.description}</p>
            </div>
            <div className='py-10 flex flex-col gap-2'>
                <h2 className='border-b text-2xl font-semibold py-3'>Özellikler</h2>
                <span className='text-lg font-semibold'>Konut Tipi: <span className='font-normal'>
                    {ad.houseType=="FLAT"?"Apartman":""}
                    {ad.houseType=="VILLA"?"Villa":""}
                    {ad.houseType=="DETACHED_HOUSE"?"Müstakil Ev":""}
                    </span></span>
                <span className='text-lg font-semibold'>m²: <span className='font-normal'>{ad.size}</span></span>
                <span className='text-lg font-semibold'>Oda Sayısı: <span className='font-normal'>{ad.numberOfRooms}+{ad.numberOfLivingRooms}</span></span>
                <span className='text-lg font-semibold'>Fiyat: <span className='font-normal'>{ad.amount}</span></span>

                {(ad.floorNumber==null)?"":<span className='text-lg font-semibold'>Bulunduğu Kat: <span className='font-normal'>{ad.floorNumber}</span></span>}
                {(ad.yardSize==null)?"":<span className='text-lg font-semibold'>Açık Alan(m²): <span className='font-normal'>{ad.yardSize}</span></span>}
                {(ad.hasPool==null)?"":<span className='text-lg font-semibold'>Havuz: <span className='font-normal'>{ad.hasPool?"var":"yok"}</span></span>}
                
                
                
            </div>
            
        </div>
  )
}
