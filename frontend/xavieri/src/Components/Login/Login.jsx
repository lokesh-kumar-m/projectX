import { useState } from "react";
import { useAuth } from "../auth";
import { useNavigate } from "react-router-dom";

import './login.css'

const Login = () => {
    const authContext=useAuth()
    const [name, setName] = useState("")
    const [password, setPassword] = useState("")
    const [ErrorMessage,setMessage]=useState('')
    const NavigateTo=useNavigate()
    async function handleLogin() {
        if(await authContext.islogin(name,password)){
            NavigateTo(`/welcome/${name}`)
        }else{
            setMessage("Please check your username and password")
        }

        console.log("clicked")
    }
    return (
        <div className="login-container">
            <div></div>
            <div className="main-container ">
                <div className="welcome-user">
                    <span className="Hello"><h2>Hello!</h2></span>
                    <h4 className="title">Bills Manager</h4>
                </div>
                <div className="login-box">
                    <div className="title">
                        <h2>Log In</h2>
                    </div>
                    <div className="form-box">
                        <div className="input-box">
                            <label>User Name</label>
                            <input type="text"
                                value={name}
                                placeholder="Enter user name"
                                onChange={e => setName(e.target.value)} />
                        </div>
                        <div className="input-box">
                            <label>Password</label>
                            <input type="password"
                                value={password}
                                placeholder="password"
                                onChange={e => setPassword(e.target.value)} />
                        </div>
                        <button className="btn" onClick={handleLogin}>Login</button>
                    </div>
                    <div className="new-user">
                        <p>New to Bill Manager?  <a href="/users/register">Register Here</a></p>
                    </div>
                </div>
            </div>
            <div></div>
        </div>
    );
}

export default Login