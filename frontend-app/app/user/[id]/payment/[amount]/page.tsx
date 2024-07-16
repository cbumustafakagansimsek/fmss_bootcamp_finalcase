import { cookies } from 'next/headers'
import { redirect } from 'next/navigation'
import React from 'react'

export default function page({params}:any) {

  async function payment() {
    "use server"

    const cookieStore = cookies()
    const response = await fetch("http://localhost:8080/api/v1/payment/validate",{
      method:"POST",
      headers:{
        Authorization:`Bearer ${cookieStore.get("token")?.value}`
      }
    })
    if(response.status==200){
      redirect(`/user/${params.id}`)
    }
    console.log(response.status==200);
  }
  return (
    <div className='container mx-auto h-[100vh] flex justify-center items-center'>
      <form action={payment} className='flex flex-col gap-5 max-w-[400px] text-lg bg-white rounded-lg shadow-lg p-5'>
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
