"use client"
import React, { FormEvent, useState } from 'react'
import { useRouter, useSearchParams } from "next/navigation";
import { useCookies } from 'next-client-cookies';
import { jwtDecode } from "jwt-decode";


export default function Login() {
  const [failure,setFailure] = useState(false);
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
    
    if (response.ok) {
      cookies.set("token",resData.token);
      const nextUrl = searchParams.get("next");
      router.push(nextUrl ?? "/");
      router.refresh();
    } else {
      setFailure(true);
    }
  };


  return (
        <div className=' border rounded-lg p-5 bg-white'>
            <h2 className='text-2xl font-semibold border-b mb-5'>GİRİŞ</h2>
            <form onSubmit={handleSubmit} className='flex flex-col gap-3'>
                <label htmlFor="mail">Email</label>
                <input className='border border-slate-600 rounded-lg p-2' type="email" id='mail' name='mail' required/>
                <label htmlFor="password">Şifre</label>
                <input className='border border-slate-600 rounded-lg p-2' type="password" id='password' name='password' required/>
                <button type='submit' className=' bg-blue-700 text-white rounded-lg p-3'>Giriş</button>
            </form>
            {failure?<span className='text-red-600'>Email veya şifre hatalı</span>:""}
        </div>
  )
}
