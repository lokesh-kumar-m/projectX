import React from "react";
import { BrowserRouter, Navigate, Route, Routes } from "react-router-dom"
import RegisterUser from "../Register/Register";
import Error from "../UnefinedPath/Error";
import Login from "../Login/Login";
import Auth, { useAuth } from "../auth";

function AuthenticatedRoutes({children}){
    const authContext=useAuth()
    if(authContext.isAuthentic){
        return children
    }
    return <Navigate to="/"></Navigate>
}

const Manager = () => {
    return (
        <div>
            <Auth>
            <BrowserRouter>
                <Routes>
                    <Route path="/" element={<RegisterUser />}/>
                    <Route path="/users/register" element={<RegisterUser />} />
                    <Route path="/users/login" element={<Login/>}/>

                    <Route path="*" element={<Error />} />
                </Routes>
            </BrowserRouter>
            </Auth>
        </div>
    );
}

export default Manager