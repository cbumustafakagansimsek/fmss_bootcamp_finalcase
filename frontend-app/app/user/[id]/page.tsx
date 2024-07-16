import { redirect } from 'next/navigation'
import React from 'react'

export default function page({params}:any) {
    redirect(`/user/${params.id}/ilan`);
  return (
    <div></div>
  )
}
