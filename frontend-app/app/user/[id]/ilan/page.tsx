import Card from '@/components/user-ads/card';
import React from 'react'
import StatusFilter from './status-ad';


const getAdForUser =async (id:string,status:string) => {
    var query = ""
    if(status){
        query = "status="+status;
    }
    const response = await fetch(`http://localhost:8080/api/v1/ads/user/${id}?${query}` 
    ,{
      cache:'no-store',
      method:'GET',
    }
    );
   return await response.json();
}

export default async function page({params,searchParams}
    :{
        params:{id:string},
        searchParams:{status:string}
    }) {
    
    const data = await getAdForUser(params.id,searchParams.status);

    return (
        <div className='container mx-auto px-4'>
            <div className='flex justify-end'>
                <StatusFilter></StatusFilter>
            </div>
            <div className='my-8 flex justify-center flex-wrap gap-8'>
                {data.map((card:any,index:number)=><Card data={card} key={index}></Card>)}
            </div>
        </div>
    )
}
