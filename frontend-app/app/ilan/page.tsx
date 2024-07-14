import Card from '@/components/card/card'
import React from 'react'

const getListing =async () => {
  const body ={
    page:0,
    size:10,
    sort:"ASC"
  }

  let query = new URLSearchParams(body).toString();
  console.log(query);
  
   const response = await fetch("http://localhost:8080/api/v1/listing?"+query
  
   ,{
     cache:'no-store',
     method:'GET',
   }
   
   );

  return await response.json();

}

export default async function page() {
  const data = await getListing();
  console.log(data);
  
  return (
    <div className='container mx-auto px-4'>
        <div className='my-32 flex justify-center flex-wrap gap-8'>
            {data.map((listing:any,index:number)=><Card listing={listing}></Card>)}
        </div>
        
    </div>
  )
}
