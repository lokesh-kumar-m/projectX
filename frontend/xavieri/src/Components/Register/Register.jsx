import React, { useState } from "react";
import { AddNewUser } from "../API/userApiRegistratoin";

import './Register.css'


const RegisterUser=()=>{
    const [username,setName]=useState("admin")
    const [emailid,setEmail]=useState("admin@123")
    const [password,setPassword]=useState("dummy")

    function handleSubmit(){
        console.log("Clicked")
        const USER={
            username: username,
            password: password,
            email:emailid
        }
        AddNewUser(USER).then(
            (response)=>console.log(response)
        ).catch(
            (error)=>console.log(error)
        )

    }
    return(
        <div className="registration">
            <div></div>
            <div className="register-container">
                <h2>Register User</h2>
            <div>
                <div className="prompt-box">
                    <label>User Name</label>
                    <input type="text"  
                    value={username} 
                    onChange={e=>setName(e.target.value)}/>
                </div>
                <div className="prompt-box">
                    <label>Email Id</label>
                    <input 
                    type="email" 
                    value={emailid} 
                    onChange={e=>setEmail(e.target.value)}/>
                </div>
                <div className="prompt-box">
                    <label>Password</label>
                    <input 
                    type="password" 
                    value={password}  
                    onChange={e=>setPassword(e.target.value)}/>
                </div>
                <button className="submt-button" onClick={handleSubmit}>Submit</button>
            </div>
            </div>
            <div></div>
        </div>
    );
}

export default RegisterUser;