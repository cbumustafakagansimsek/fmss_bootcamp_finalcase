import Card from '@/components/user-listings/card';
import React from 'react'
const getListingForUser =async (id:string) => {
    const response = await fetch(`http://localhost:8080/api/v1/listing/user/${id}?status=ACTIVE` 
    ,{
      cache:'no-store',
      method:'GET',
    }
    );
   return await response.json();
}

export default async function page({params}:any) {
    const data = await getListingForUser(params.id);
    return (
        <div>
            <div className='my-32 flex justify-center flex-wrap gap-8'>
                {data.map((card:any,index:number)=><Card data={card} key={index}></Card>)}
            </div>
        </div>
    )
}
