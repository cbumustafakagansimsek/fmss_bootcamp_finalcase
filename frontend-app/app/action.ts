"use server"
import { jwtDecode } from "jwt-decode";
import { cookies } from "next/headers";


export async function getTokenPayload() {

    const token=cookies().get("token");
    
    if (token && token.value!='') {

        return jwtDecode(token.value);
    }
    return null;
}
