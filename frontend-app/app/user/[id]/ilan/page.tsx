import Card from '@/app/user/[id]/ilan/card';
import React from 'react'
import StatusFilter from './status-ad';
import Listing from './listing';



export default  function page({params,searchParams}
    :{
        params:{id:string},
        searchParams:{status:string}
    }) {
    
    
        
    return (
        <div className='container mx-auto px-4'>
            <div className='flex justify-end'>
                <StatusFilter></StatusFilter>
            </div>
            <div className='my-8'>
                <Listing params={params} searchParams={searchParams}></Listing>
            </div>
        </div>
    )
}
