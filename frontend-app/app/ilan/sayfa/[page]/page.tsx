
import Listing from '@/app/ilan/sayfa/[page]/listing';
import React, { Suspense, useState } from 'react'
import SearchListing from './search-listing';

export default function page({params,searchParams}:any) {

  return (
    <div className='container mx-auto px-4'>
      <div>
       <SearchListing></SearchListing>
      </div>
        <Listing page={params.page} searchParams={searchParams}></Listing>
    </div>
  )
}
