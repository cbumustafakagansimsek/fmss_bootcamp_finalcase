import { cookies } from 'next/headers';
import Link from 'next/link';
import React from 'react'
import { ImCross } from 'react-icons/im';

const getSubscriptionsForUser =async () => {
    const cookie = cookies()
    const response = await fetch(`http://localhost:8080/api/v1/subscriptions/secure/user` 
    ,{
      method:'GET',
      cache:'no-store',
        headers:{
            Authorization:`Bearer ${cookie.get("token")?.value}`,
        }

    }
    );
    
   return await response.json();
}

export default async function page({params}:{params:{id:number}}) {
    const data = await getSubscriptionsForUser();
  return (
    <div className='flex justify-center absolute top-0 w-[100vw] h-[100vh] bg-black bg-opacity-30'>
        <div className='my-32 bg-white rounded-lg shadow-lg p-5 overflow-y-auto'>
            <Link href={`/user/${params.id}/ilan`} className='flex justify-end'><ImCross className='text-red-600' /></Link>
            <table className="border-b border-neutral-200 font-medium">       
                <thead>
                    <tr>
                        <th className='px-6 py-4'></th>
                        <th className='px-6 py-4'>İlan Limiti</th>
                        <th className='px-6 py-4'>Başlama Tarihi</th>
                        <th className='px-6 py-4'>Bitiş Tarihi</th>
                    </tr>
                </thead>
                <tbody>
                    {data.map((sub:any,index:number)=>
                    <tr key={index} className='border-b border-neutral-200'>
                        <td className='whitespace-nowrap px-6 py-4 font-medium'>{index+1}</td>
                        <td className='whitespace-nowrap px-6 py-4'>{sub.adLimit}</td>
                        <td className='whitespace-nowrap px-6 py-4'>{sub.startDate}</td>
                        <td className='whitespace-nowrap px-6 py-4'>{sub.endDate}</td>
                    </tr>
                    )}
                </tbody>
            </table>
        </div>
    </div>
  )
}
