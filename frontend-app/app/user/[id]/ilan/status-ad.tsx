"use client"
import { useSearchParams, usePathname, useRouter } from 'next/navigation';
import { FormEvent, useState } from 'react';

export default function StatusFilter() {
    const searchParams = useSearchParams();
    const pathname = usePathname();
    const { replace } = useRouter();
    const[status,setStatus] = useState("")
   
    function handleFilter(status:string) {

     
        const params = new URLSearchParams(searchParams);
        if(status!=""){
            params.set("status",status);
        }else{
            params.delete("status");
        }
        replace(`${pathname}?${params.toString()}`)
    }
    return(
        <div className='bg-white p-5 rounded-lg shadow-lg w-60 flex justify-center items-center'>
                        <label htmlFor="" className='text-lg px-3'>Status</label>
                        <select name="status" id="status" defaultValue="" onChange={(e)=>handleFilter(e.target.value)} className='border p-3'>
                            <option value="">Hepsi</option>
                            <option value="ACTIVE">Aktif</option>
                            <option value="PASSIVE">Pasif</option>
                        </select>                        

        </div>)}