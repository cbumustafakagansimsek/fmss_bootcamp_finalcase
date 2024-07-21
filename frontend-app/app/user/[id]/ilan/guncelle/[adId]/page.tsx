import { cookies } from 'next/headers';
import Link from 'next/link';
import { notFound, redirect } from 'next/navigation';
import React from 'react'
import { ImCross } from 'react-icons/im';


const getAd=async (id:string) => {
  const cookiess = cookies();
    try{
        const response = await fetch("http://localhost:8080/api/v1/ads/secure/"+id,{
          cache:"no-store",
          headers:{
            Authorization:`Bearer ${cookiess.get("token")?.value}`
          }
        });
        if(!response.ok){
            throw new Error("Not Found");
        }
        return await response.json();
    }catch(error){
        console.log(error );
        notFound();
    }
}

export default async function page({params}:{
  params:{
    adId:string,
    id:string
}}) {
  const data = await getAd(params.adId);
  console.log(data);
  
  async function update(formData:FormData) {
    "use server"
    const cookiess = cookies();
    const response = await fetch(`http://localhost:8080/api/v1/ads/secure/status?status=${formData.get("status")}&id=${formData.get("id")}`,{
            method:"PUT",
            headers:{
                Authorization:`Bearer ${cookiess.get("token")?.value}`,
            },
            cache:"no-store" 
        })
        
        if(response.ok){
          redirect(`/user/${params.id}/ilan`);
        }
    
  }

  async function deleteAd(formData:FormData) {
    "use server"
    const cookiess = cookies();
    const response = await fetch(`http://localhost:8080/api/v1/ads/secure/delete/${formData.get("id")}`,{
            method:"DELETE",
            headers:{
                Authorization:`Bearer ${cookiess.get("token")?.value}`,
            },
            cache:"no-store" 
        })
        
        if(response.ok){
          redirect(`/user/${params.id}/ilan`);
        }
    
  }
  return (

    <div className=' absolute w-full bg-black flex justify-center items-center h-[100vh] top-0 bg-opacity-40'>
      <div className='flex flex-col bg-white p-5 rounded-lg gap-1 min-w-80'>
        <Link href={`/user/${params.id}/ilan`} className='flex justify-end'><ImCross className='text-red-600' /></Link>
        <span className='text-lg font-semibold'>İlan Başlığı: <span className='font-normal'>{data.title}</span></span>
        <span className='text-lg font-semibold'>Açıklama: <span className='font-normal'>{data.description}</span></span>

        <span className='text-lg font-semibold'>Konut Tipi: <span className='font-normal'>
        {data.houseType=="FLAT"?"Apartman":""}
        {data.houseType=="VILLA"?"Villa":""}
        {data.houseType=="DETACHED_HOUSE"?"Müstakil Ev":""}
        </span></span>   
        <span className='text-lg font-semibold'>İl: <span className='font-normal'>{data.province}</span></span>
        <span className='text-lg font-semibold'>İlçe: <span className='font-normal'>{data.district}</span></span>
        <span className='text-lg font-semibold'>m²: <span className='font-normal'>{data.size}</span></span>
        <span className='text-lg font-semibold'>Oda Sayısı: <span className='font-normal'>{data.numberOfRooms}+{data.numberOfLivingRooms}</span></span>
        <span className='text-lg font-semibold'>Fiyat: <span className='font-normal'>{data.amount}</span></span>
          {(data.floorNumber==null)?"":<span className='text-lg font-semibold'>Bulunduğu Kat: <span className='font-normal'>{data.floorNumber}</span></span>}
          {(data.yardSize==null)?"":<span className='text-lg font-semibold'>Açık Alan(m²): <span className='font-normal'>{data.yardSize}</span></span>}
          {(data.hasPool==null)?"":<span className='text-lg font-semibold'>Havuz: <span className='font-normal'>{data.hasPool?"var":"yok"}</span></span>}
          <form action={update} className='flex flex-col gap-1'>
              <div className='flex gap-1'>
                <label htmlFor="">Aktif</label>
                <input type="radio" name='status' value={"ACTIVE"} defaultChecked={data.status === "ACTIVE"}/>
                <label htmlFor="">Pasif</label>
                <input type="radio" name='status' value={"PASSIVE"} defaultChecked={data.status === "PASSIVE"}/>
                <input type="number" name='id' defaultValue={data.id} className='hidden' />
              </div>
              <button className='flex justify-center items-center bg-black text-white text-xl rounded-lg border-2 border-black hover:bg-white hover:text-black font-semibold p-4'>Güncelle</button>

          </form>
          <form action={deleteAd}>
            <input type="number" name='id' defaultValue={data.id} className='hidden' />
            <button className='flex justify-center items-center w-full bg-red-600 text-white text-xl rounded-lg border-2 border-red-600 hover:bg-white hover:text-black font-semibold p-4'>Sil</button>
          </form>
      </div>
    </div>
  )
}
