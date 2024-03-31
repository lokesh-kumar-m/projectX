import { apiContext } from "./apiContext";

export const AddNewUser=(USER)=>apiContext.post(`/users/register`,USER)

export const AuthenticateUser=(INFO)=>apiContext.post(`/users/login`,INFO)

export const JwtTest=()=>apiContext.get(`/users/Hello`)