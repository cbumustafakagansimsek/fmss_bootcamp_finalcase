import React from 'react'

export default function page() {
  return (
    <div className='flex justify-center items-center h-[100vh]'>
        <div className=' border rounded-lg p-5 bg-white'>
            <h2 className='text-2xl font-semibold border-b mb-5'>KAYIT</h2>
            <form action="" className='flex flex-col gap-3'>
                <label htmlFor="name">İsim</label>
                <input className='border border-slate-600 rounded-lg p-2' type="text" id='name' name='name'/>
                <label htmlFor="surname">Soyisim</label>
                <input className='border border-slate-600 rounded-lg p-2' type="text" id='surname' name='surname'/>
                <label htmlFor="mail">Email</label>
                <input className='border border-slate-600 rounded-lg p-2' type="email" id='email' name='mail'/>
                <label htmlFor="password">Şifre</label>
                <input className='border border-slate-600 rounded-lg p-2' type="password" id='password' name='password'/>
                <button className=' bg-blue-700 text-white rounded-lg p-3'>Giriş</button>
            </form>
        </div>
    </div>
  )
}
