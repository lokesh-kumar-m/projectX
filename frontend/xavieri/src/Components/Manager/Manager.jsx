import React,{useState,useEffect} from "react";
import { BrowserRouter, Navigate, Route, Routes } from "react-router-dom"
import RegisterUser from "../Register/Register";
import Error from "../UnefinedPath/Error";
import Login from "../Login/Login";
import Auth, { useAuth } from "../auth";
import Header from "../Header/Header";
import Welcome from "../Home/Welcome";
import BillSplit from "../BillSplit/Split";
import { expenseType } from "../API/globalVals";

function AuthenticatedRoutes({children}){
    const authContext=useAuth()
    if(authContext.isAuthentic){
        return children
    }
    return <Navigate to="/"></Navigate>
}

const Manager = () => {

    const [currencies, setCurrencies] = useState([]);
    const [friendsList,setList]=useState([])
    useEffect(() => {
        const fetchExchangeRates = async () => {
            try {
                const response = await fetch('https://open.er-api.com/v6/latest/SEK');
                const data = await response.json();
                setCurrencies((prevData)=>{
                    const updated=[
                        {
                            "currency":"SEK",
                            "rate":1
                        },
                        {
                            "currency":"SEK",
                            "rate":data.rates.INR
                        },
                        {
                            "currency":"USD",
                            "rate":data.rates.USD
                        },
                        {
                            "currency":"EUR",
                            "rate":data.rates.EUR
                        }
                    ]
                    return updated    
                })
            } catch (error) {
                console.error("Error fetching exchange rates:", error);
            }
        };

        fetchExchangeRates();
    }, []);

    return (
        <div>
            <Auth>
            <BrowserRouter>
            <Header/>
                <Routes>
                    <Route path='/' element={<RegisterUser />}/>
                    <Route path='/users/register' element={<RegisterUser />} />
                    <Route path='/users/login' element={<Login/>}/>
                    <Route path='/welcome/:username' element={
                        <AuthenticatedRoutes>
                            <Welcome 
                            currency={currencies}
                            type={expenseType}
                            friendsList={friendsList}
                            setList={setList}
                            ></Welcome>
                        </AuthenticatedRoutes>
                    }/>
                    <Route path="/add/bills" element={
                        <AuthenticatedRoutes>
                            <BillSplit 
                            currencies={currencies}
                            type={expenseType}
                            friendsList={friendsList}
                            ></BillSplit>
                        </AuthenticatedRoutes>
                    }/> 

                    <Route path="*" element={<Error />} />
                </Routes>
            </BrowserRouter>
            </Auth>
        </div>
    );
}

export default Manager