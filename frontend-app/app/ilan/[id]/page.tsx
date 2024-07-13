import React from 'react'
import Image from 'next/image'

export default function page() {
  return (
    <div className='py-32 container mx-auto px-4'>
        <div className='bg-white p-5 my-5 rounded-lg'>
            <h2 className='border-b text-2xl font-semibold py-3'>Yayınlayan</h2>
            <div className='flex flex-col gap-2'>
                <span className='text-xl font-semibold'>İsim: <span className='font-normal'>mustafa</span></span>
                <span className='text-xl font-semibold'>Soyisim: <span className='font-normal'>mustafa</span></span>
                <span className='text-xl font-semibold'>Yayınlana Tarihi: <span className='font-normal'>mustafa</span></span>
            </div>
            

        </div>
        <div className='bg-white p-5 my-5 rounded-lg'>
            <h1 className='text-2xl font-semibold py-5'>Lorem ipsum dolor sit amet.</h1>
            <div className='relative h-[50vw]'>
            <Image
                src={"/home_image.jpeg"}
                fill={true}
                sizes="(min-width: 808px) 50vw, 100vw"
                objectFit='cover'
                alt="image"
            />
            </div>
            <div className='py-10'>
                <h2 className='border-b text-2xl font-semibold py-3'>Açıklama</h2>
                <p className='text-slate-600 py-3'>Lorem ipsum dolor sit, amet consectetur adipisicing elit. Fugit ex error deserunt qui possimus sit consequuntur fugiat numquam deleniti consectetur est at, maxime soluta odio reprehenderit tempore totam veritatis recusandae?
                Vel beatae rem, nemo debitis deleniti vero autem cumque nisi voluptate adipisci, fugiat minima aspernatur laboriosam minus placeat iure distinctio perferendis cum eveniet doloremque repudiandae necessitatibus hic atque provident! Nihil!
                Pariatur consequuntur, in quam neque optio nobis facere et, consequatur molestiae aliquam distinctio quisquam laboriosam quia modi repudiandae sed dicta deleniti! Consequatur quisquam optio molestiae vitae in officiis nam officia.
                Hic odit perspiciatis neque, nulla quia accusamus modi veritatis nostrum ab, saepe est et in ratione aspernatur ipsa facere atque dolore recusandae vel assumenda? Odio et eius minima vero laboriosam.
                Quos placeat facere earum est nam nulla quo sequi a soluta, perspiciatis esse praesentium nisi nobis amet fugiat quaerat distinctio. Quaerat deleniti nihil alias repudiandae incidunt voluptatem quisquam labore earum.</p>
            </div>
            <div className='py-10'>
                <h2 className='border-b text-2xl font-semibold py-3'>Özellikler</h2>
                <p className='text-slate-600 py-3'>Lorem ipsum dolor sit, amet consectetur adipisicing elit. Fugit ex error deserunt qui possimus sit consequuntur fugiat numquam deleniti consectetur est at, maxime soluta odio reprehenderit tempore totam veritatis recusandae?
                Vel beatae rem, nemo debitis deleniti vero autem cumque nisi voluptate adipisci, fugiat minima aspernatur laboriosam minus placeat iure distinctio perferendis cum eveniet doloremque repudiandae necessitatibus hic atque provident! Nihil!
                Pariatur consequuntur, in quam neque optio nobis facere et, consequatur molestiae aliquam distinctio quisquam laboriosam quia modi repudiandae sed dicta deleniti! Consequatur quisquam optio molestiae vitae in officiis nam officia.
                Hic odit perspiciatis neque, nulla quia accusamus modi veritatis nostrum ab, saepe est et in ratione aspernatur ipsa facere atque dolore recusandae vel assumenda? Odio et eius minima vero laboriosam.
                Quos placeat facere earum est nam nulla quo sequi a soluta, perspiciatis esse praesentium nisi nobis amet fugiat quaerat distinctio. Quaerat deleniti nihil alias repudiandae incidunt voluptatem quisquam labore earum.</p>
            </div>
            
        </div>
        
         
       
    </div>
  )
}
