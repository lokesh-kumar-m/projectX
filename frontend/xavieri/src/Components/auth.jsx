import { createContext, useContext, useState } from "react";
import { apiContext } from "./API/apiContext";

export const AuthContext=createContext()
export const useAuth=()=>useContext(AuthContext)
const Auth=({children})=>{
    const [isAuthentic,setAuthentic]=useState(false)
    const [username,setName]=useState('')
    const [authToken,setToken]=useState('')
    async function islogin(username,password){
        try{
            //api call to check if user exists
            var response=false
            if(response){
                const token=""
            const jwtToken='Bearer '+token
            setToken(jwtToken)
            setName(username)
            setAuthentic(true)
            // apiContext.interceptors.request.use(
            //     (config)=>{
            //         config.headers.Authorization=jwtToken
            //         return config
            //     }
            // )
            return true;
            }
            else{
                setAuthentic(false)
                return false
            }

        }catch(error){
            console.log(error)
        }
    }

    function islogout(){
        setToken('')
        setAuthentic(false)
    }
    
    return(
        <AuthContext.Provider value={{username,isAuthentic,authToken}}>
            {children}
        </AuthContext.Provider>
    );
}

export default Auth