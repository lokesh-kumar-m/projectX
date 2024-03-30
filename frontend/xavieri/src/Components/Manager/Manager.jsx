import React from "react";
import { BrowserRouter, Navigate, Route, Routes } from "react-router-dom"
import RegisterUser from "../Register/Register";
import Error from "../UnefinedPath/Error";

const Manager = () => {
    return (
        <div>
            <BrowserRouter>
                <Routes>
                    <Route path="/" element={<RegisterUser />}/>
                    <Route path="/users/register" element={<RegisterUser />} />

                    <Route path="*" element={<Error />} />
                </Routes>
            </BrowserRouter>
        </div>
    );
}

export default Manager