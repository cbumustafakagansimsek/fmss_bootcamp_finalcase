import { getTokenPayload } from '@/app/action';
import { exitAcount } from '@/libs/auth/auth';
import { JwtPayload } from 'jwt-decode';
import Link from 'next/link'
import { redirect } from 'next/navigation';
import React from 'react'
import { FaRegUser } from "react-icons/fa";




export default async function Header() {
  const payload:any|null  = await getTokenPayload();

  async function exit() {
    "use server"
    
    exitAcount();
    redirect("/giris")
    
  }
  
  return (
    <div className='w-full h-20 bg-white fixed top-0 left-0 z-10 border-b-2'>
        <div className='flex justify-between h-full w-full container mx-auto p-5'>
            <div className=' flex text-2xl font-semibold'>
            <Link href={"/ilan/sayfa/1"}>İLANLAR</Link>

            </div>
            <div className='flex'>
              {payload==null?<>
                <Link href={"/giris"} className='px-5 text-lg font-semibold'>Giriş</Link>
                <Link href={"/kayit"} className='px-5 text-lg font-semibold'>Kayıt</Link>
                </>
              :<>
              <Link href={`/user/${payload.sub}`} className='flex items-center'><FaRegUser className='mx-2' />{payload.name}</Link>
              <form action={exit} className='flex items-center'>
              <button className='px-5 text-lg font-semibold '>Çıkış</button>

              </form>
              </>}
                
                
            </div>
        </div>
    </div>
  )
}
