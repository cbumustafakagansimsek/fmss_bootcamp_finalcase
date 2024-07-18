"use client"
import { useSearchParams, usePathname, useRouter } from 'next/navigation';
import { FormEvent } from 'react';

export default function SearchAd() {
    const searchParams = useSearchParams();
    const pathname = usePathname();
    const { replace } = useRouter();
   
    function handleSearch(event: FormEvent<HTMLFormElement>) {
        event.preventDefault();
        const formData= new FormData(event.currentTarget)

     
        const params = new URLSearchParams(searchParams);
 
        params.set("province",formData.get("province") as string)
        params.set("district",formData.get("district") as string)
        params.set("minSize",formData.get("minSize") as string)
        params.set("maxSize",formData.get("maxSize") as string)
        params.set("numberOfRooms",formData.get("numberOfRooms") as string)
        params.set("numberOfLivingRooms",formData.get("numberOfLivingRooms") as string)


        replace(`${pathname}?${params.toString()}`)
    }
    return(
        <div className='bg-white p-5 rounded-lg shadow-lg'>
            <form onSubmit={handleSearch} >
                <div className='my-32 flex justify-center flex-wrap gap-8'>
                    <div >
                        <label htmlFor="" className='text-lg'>Şehir</label>
                        <input type="text" name='province' className='block border-2 rounded-lg p-2' />
                    </div>
                    <div>
                        <label htmlFor="" className='text-lg'>İlçe</label>
                        <input type="text" name='district' className='block border-2 rounded-lg p-2' />
                    </div>
                    <div>
                        <label htmlFor="" className='text-lg'>Min m²</label>
                        <input type="number" name='minSize' className='block border-2 rounded-lg p-2' />
                    </div>
                    <div>
                        <label htmlFor="" className='text-lg'>Max m²</label>
                        <input type="number" name='maxSize' className='block border-2 rounded-lg p-2' />
                    </div>
                    <div>
                        <label htmlFor="" className='text-lg'>Oda Sayısı</label>
                        <input type="number" name='numberOfRooms' className='block border-2 rounded-lg p-2' />
                    </div>
                    <div>
                        <label htmlFor="" className='text-lg'>Salon Sayısı</label>
                        <input type="number" name='numberOfLivingRooms' className='block border-2 rounded-lg p-2' />
                    </div>
                </div>
                
                <button type='submit' className='flex justify-center items-center bg-black text-white text-xl rounded-lg border-2 border-black hover:bg-white hover:text-black font-semibold p-2 w-full'>Ara</button>

            </form>
        </div>
    );
  }