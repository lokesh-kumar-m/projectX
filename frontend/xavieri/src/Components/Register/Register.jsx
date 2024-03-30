import React, { useState } from "react";
import { AddNewUser } from "../API/userApiRegistratoin";

import './Register.css'


const RegisterUser = () => {
    const [username, setName] = useState("")
    const [emailid, setEmail] = useState("")
    const [password, setPassword] = useState("")

    function handleSubmit() {
        const USER = {
            username: username,
            password: password,
            email: emailid
        }
        AddNewUser(USER).then(
            (response) => isCreated(response)
        ).catch(
            (error) => isCreated(error.response)
        )
    }
    function isCreated(response) {
        if (response.status == 200) {
            console.log("USer Created successfully")
        }
        else if (response.status == 400) {
            console.log("A user with the username already exists")
        }
    }

    return (
        <div className="registration">
            <div></div>
            <div className="register-container">
                <h2>Register User</h2>
                <div>
                    <div className="prompt-box">
                        <label>User Name</label>
                        <input type="text"
                            value={username}
                            onChange={e => setName(e.target.value)} />
                    </div>
                    <div className="prompt-box">
                        <label>Email Id</label>
                        <input
                            type="email"
                            value={emailid}
                            onChange={e => setEmail(e.target.value)} />
                    </div>
                    <div className="prompt-box">
                        <label>Password</label>
                        <input
                            type="password"
                            value={password}
                            onChange={e => setPassword(e.target.value)} />
                    </div>
                    <button className="submt-button" onClick={handleSubmit}>Submit</button>
                </div>
            </div>
            <div></div>
        </div>
    );
}

export default RegisterUser;