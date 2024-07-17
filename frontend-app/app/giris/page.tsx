"use client"
import React, { FormEvent } from 'react'
import { useRouter, useSearchParams } from "next/navigation";
import { useCookies } from 'next-client-cookies';


export default function page() {
  const router = useRouter();
  const searchParams = useSearchParams();
  const cookies = useCookies();

  const handleSubmit = async (event: FormEvent<HTMLFormElement>) => {
    event.preventDefault();
    const formData = new FormData(event.currentTarget)
  
    const request = {
      mail:formData.get("mail"),
      password:formData.get("password")
    }
  
  
    const response =await fetch('http://localhost:8080/api/v1/auth/login', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',      
      },
      body: JSON.stringify(request),
    });
    const resData  = await response.json();
    
    if (resData) {
      cookies.set("token",resData.token);
      const nextUrl = searchParams.get("next");
      router.push(nextUrl ?? "/user/1");
      router.refresh();
    } else {
      alert("Login failed");
    }
  };


  return (
    <div className='flex justify-center items-center '>
      
        <div className=' border rounded-lg p-5 bg-white'>
            <h2 className='text-2xl font-semibold border-b mb-5'>GİRİŞ</h2>
            <form onSubmit={handleSubmit} className='flex flex-col gap-3'>
                <label htmlFor="mail">Email</label>
                <input className='border border-slate-600 rounded-lg p-2' type="email" id='mail' name='mail'/>
                <label htmlFor="password">Şifre</label>
                <input className='border border-slate-600 rounded-lg p-2' type="password" id='password' name='password'/>
                <button type='submit' className=' bg-blue-700 text-white rounded-lg p-3'>Giriş</button>
            </form>
        </div>
    </div>
  )
}
