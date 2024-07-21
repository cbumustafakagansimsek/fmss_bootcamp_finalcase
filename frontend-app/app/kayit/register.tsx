"use client"
import { useRouter } from 'next/navigation';
import React, { FormEvent, useState } from 'react'

export default function Register() {
  const [error,setError] = useState("");
  const router = useRouter();

  const handleSubmit = async (event: FormEvent<HTMLFormElement>) => {
    event.preventDefault();
    const formData = new FormData(event.currentTarget)
  
    const request = {
      name:formData.get("name"),
      surname:formData.get("surname"),
      mail:formData.get("mail"),
      password:formData.get("password")
    }
  
  
    const response =await fetch('http://localhost:8080/api/v1/auth/register', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',      
      },
      body: JSON.stringify(request),
    });
    
    if (response.status==201) {
      router.push("/giris");
      router.refresh();
    }else{
      const resData  = await response.json();
      setError(resData.message);
    }
    
  };
  return (
        <div className=' border rounded-lg p-5 bg-white'>
            <h2 className='text-2xl font-semibold border-b mb-5'>KAYIT</h2>
            <form onSubmit={handleSubmit} className='flex flex-col gap-3'>
                <label htmlFor="name">İsim</label>
                <input className='border border-slate-600 rounded-lg p-2' type="text" id='name' name='name' required/>
                <label htmlFor="surname">Soyisim</label>
                <input className='border border-slate-600 rounded-lg p-2' type="text" id='surname' name='surname' required/>
                <label htmlFor="mail">Email</label>
                <input className='border border-slate-600 rounded-lg p-2' type="email" id='email' name='mail' required/>
                <label htmlFor="password">Şifre</label>
                <input className='border border-slate-600 rounded-lg p-2' type="password" id='password' name='password' required/>
                <button className=' bg-blue-700 text-white rounded-lg p-3'>Giriş</button>
            </form>
            <span className='text-red-600 text-center'>{error}</span>
        </div>
  )
}