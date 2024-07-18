import { cookies } from 'next/headers'
import Link from 'next/link'
import { redirect } from 'next/navigation'
import React from 'react'

export default function page({params}:any) {

  async function payment() {
    "use server"

    const cookieStore = cookies()
    const response = await fetch(`http://localhost:8080/api/v1/payment/validate?productAmount=${params.amount}`,{
      method:"POST",
      headers:{
        Authorization:`Bearer ${cookieStore.get("token")?.value}`
      }
    })
    if(response.status==200){
      redirect(`/user/${params.id}`)
    }
  }
  return (
    <div className='w-full mx-auto h-[100vh] flex justify-center items-center absolute top-0 bg-black bg-opacity-40'>
      <form action={payment} className='flex flex-col gap-5 max-w-[400px] text-lg bg-white rounded-lg shadow-lg p-5'>
        <span className='text-red-600 font-semibold text-right'><Link href={`/user/${params.id}/ilan`} >İptal</Link></span>
        <span>{params.amount} Aylık Abonelik</span>
          <div className='flex flex-col'>
            <label htmlFor="">Kart Sahibi</label>
            <input type="text" className='block border-2 rounded-lg p-2'/>
          </div>
          <div className='flex flex-col'>
            <label htmlFor="">CCV</label>
            <input type="text" className='block border-2 rounded-lg p-2' />
          </div>
          <div className='flex flex-col'>
            <label htmlFor="">Kart Numarası</label>
            <input type="text" className='block border-2 rounded-lg p-2'/>
          </div>
          <div className='flex flex-col'>
            <label htmlFor="">Son Kullanım Tarihi</label>
            <input type="text" className='block border-2 rounded-lg p-2'/>
          </div>
        <button type='submit' className='bg-green-600 rounded-lg shadow-lg text-xl text-white p-5'>Ödemeyi tamamla</button>
      </form>
    </div>
  )
}
