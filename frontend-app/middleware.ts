import { NextRequest, NextResponse } from "next/server";
import { verifyJwtToken } from "./libs/auth/auth";
import { jwtDecode } from "jwt-decode";

const AUTH_PAGES = ['/giris','/kayit'];

const isAuthPages = (url:string)=> AUTH_PAGES.some(page => page.startsWith(url))

export async function middleware(request:NextRequest){
    const {url,nextUrl, cookies} = request;
    const { value: token } = cookies.get("token") ?? { value: null };
    
    const hasVerifiedToken = token && (await verifyJwtToken(token));
    const isAuthPageRequested = isAuthPages(nextUrl.pathname);

       

    if(isAuthPageRequested){
        if(!hasVerifiedToken){
            const response = NextResponse.next();
            response.cookies.delete("token");
            return response;       
        }

        const response = NextResponse.redirect(new URL("/ilan/sayfa/1",url));
        return response;
    }

    
    if(!hasVerifiedToken) {
        const searchParams = new URLSearchParams();
        searchParams.set('next',nextUrl.pathname);
        const response = NextResponse.redirect(
      new URL(`/giris?${searchParams}`, url)
    );
    response.cookies.delete("token");

    return response;
    }

    if (nextUrl.pathname.startsWith("/user/")&&token) {
        var id =jwtDecode(token).sub
        if(nextUrl.pathname.split("/")[2]!=id){
          return NextResponse.redirect(new URL(`/user/${id}/ilan`,url));
        }
    } 
}

export const config={
    matcher : ["/giris","/user/:path*"]
}