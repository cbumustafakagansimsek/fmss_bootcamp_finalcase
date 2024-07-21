import React from 'react'
import Card from './card';

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
export default async function Listing({params,searchParams}
    :{
        params:{id:string},
        searchParams:{status:string}
    }) {

    const data = await getAdForUser(params.id,searchParams.status);
    console.log(data);
    

  return (
    <div className='flex justify-center flex-wrap gap-8'>{data.map((card:any,index:number)=><Card data={card} key={index}></Card>)}</div>
  )
}
