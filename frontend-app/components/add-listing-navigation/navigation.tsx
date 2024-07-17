import Link from 'next/link'
import React from 'react'

export default function AddListingNavigation({id}:any) {
  return (
    <div className='grid grid-cols-1 md:grid-cols-3 gap-5 '>
        <Link href={`/user/${id}/ilan-ekle/apartman`} className='flex justify-center items-center bg-black text-white text-xl rounded-lg border-2 border-black hover:bg-white hover:text-black font-semibold h-20'>Apartman
        </Link>
        <Link href={`/user/${id}/ilan-ekle/mustakil-ev`} className='flex justify-center items-center bg-black text-white text-xl rounded-lg border-2 border-black hover:bg-white hover:text-black font-semibold h-20'>
            Müstakil Ev
        </Link>
        <Link href={`/user/${id}/ilan-ekle/villa`} className='flex justify-center items-center bg-black text-white text-xl rounded-lg border-2 border-black hover:bg-white hover:text-black font-semibold h-20'>
            Villa
        </Link>
    </div>
  )
}
