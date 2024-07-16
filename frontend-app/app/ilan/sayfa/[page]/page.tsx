
import Listing from '@/components/listing/listing';
import React, { Suspense, useState } from 'react'

type searchRequest ={
    page:number,
    size:number,
    sort:string,
    province:string,
    district:string,
    minSize:number,
    maxSize:number,
    numberOfRooms:number,
    numberOfLivingRooms:number
}

export default function page({params}:any) {
  // const [page,setPage] = useState(+params.page-1);
  // const [size,setSize] = useState(5);
  // const [sort,setSort] = useState("ASC");
  // const [province,setProvince] = useState(null);
  // const [district,setDistrict] = useState(null);
  // const [minSize,setMinSize] = useState(null);
  // const [maxSize,setMaxSize] = useState(null);
  // const [numberOfRooms,setNumberOfRooms] = useState(null);
  // const [numberOfLivingRooms,setNumberOfLivingRooms] = useState(null);



  return (
    <div className='container mx-auto px-4'>
      <div>
        <form action="">

        </form>
      </div>
        <Listing page={params.page}></Listing>
    </div>
  )
}
