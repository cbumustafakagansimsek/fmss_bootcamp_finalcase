import Card from '@/components/card/card'
import Link from 'next/link';
import React from 'react'
import { FaArrowLeft,FaArrowRight  } from "react-icons/fa6";

const getListing =async (body:any) => {
  

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

export default async function Listing({page}:any) {
  const body ={
    page:+page-1,
    size:5,
    sort:"ASC"
  }
  const data = await getListing(body);
  const listings = data.response;
  console.log(data);

  
  

  let Pagination =()=>{
    var lists: Array<any> = new Array();
    for (let index = 0; index < data.totalPageNumber; index++) {
      lists.push(<li key={index}>
        <Link href={"/ilan/sayfa/"+(index+1)} className="flex items-center justify-center px-4 h-10 text-gray-500 bg-white border border-gray-300 hover:bg-gray-100 hover:text-gray-700 ">{index+1}</Link>
      </li>)  
    }
    return lists.map((list:any)=>{
      return list;
    })

  }
  return (
    <div className='container mx-auto px-4'>
        

        <div className='my-32 flex justify-center flex-wrap gap-8'>
            {listings.map((listing:any,index:number)=><Card key={index} listing={listing}></Card>)}
        </div>

        <nav className='flex justify-center'>
          <ul className="flex h-10">
            <li>
              <Link href={`/ilan/sayfa/${page==1?"1":+page-1}`} className="flex items-center justify-center px-4 h-10 ms-0 text-gray-500 bg-white border border-e-0 border-gray-300 rounded-s-lg hover:bg-gray-100 hover:text-gray-700"><FaArrowLeft /></Link>
            </li>
            <Pagination></Pagination>  
            <li>
              <Link href={`/ilan/sayfa/${page==data.totalPageNumber?data.totalPageNumber:+page+1}`} className="flex items-center justify-center px-4 h-10  text-gray-500 bg-white border border-gray-300 rounded-e-lg hover:bg-gray-100 hover:text-gray-700"><FaArrowRight /></Link>
            </li>
          </ul>
        </nav>
    </div>
  )
}
