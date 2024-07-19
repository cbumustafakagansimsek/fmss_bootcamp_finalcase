
import Ad from '@/app/ilan/sayfa/[page]/ad';
import React, { Suspense, useState } from 'react'
import SearchAd from './search-ad';

export default function page({params,searchParams}:any) {

  return (
    <div className='container mx-auto px-4'>
      <div>
       <SearchAd></SearchAd>
      </div>
      <Suspense fallback={<p className='w-full text-center'>Loading...</p>}>
        <Ad page={params.page} searchParams={searchParams}></Ad>
      </Suspense>
    </div>
  )
}
