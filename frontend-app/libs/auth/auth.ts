import { jwtVerify } from "jose";
import { jwtDecode } from "jwt-decode";

//  export function getJwtSecretKey() {
//    const secret =process.env.JWT_SECRET_KEY;

//    if (!secret) {
//      throw new Error("JWT Secret key is not matched");
//    }

//    return new TextEncoder().encode(secret);
//  }



export async function verifyJwtToken(token:any) {
  try {

    const res  = await fetch("http://localhost:8080/api/v1/auth/validateToken?token="+token,{
        method:"POST",
        cache:"no-store"
    });

    if (res.status==200) {
        const payload = jwtDecode(token);
        return payload;
    }else{
        throw new Error("Token not valid")
    }


  } catch (error) {
    return null;
  }
}