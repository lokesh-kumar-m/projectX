import { useEffect, useState } from "react";
import {useNavigate} from "react-router-dom"
import { useAuth } from "../auth";
import { getMyList,addFriend, removeFriend } from "../API/friendsApiService";
import Button from '@mui/material/Button';
import { FRIEND } from "../API/globalVals";
import './home.css'
import MoneyChart from "./Bar";
import ExpensesPie from "./pie";


const Welcome=({friendsList,expenseType,currencies,setList})=>{
    const authContext=useAuth()
    
    const [newFriend,setNewFriend]=useState('')
    const [amount, setAmount]=useState(0)
    const navigateTo=useNavigate();
    useEffect(
        ()=>getList(),[]
    )
    function getList(){
        getMyList(authContext.username).then(
            (response)=>setList(response.data)
        ).catch(
            (error)=>console.log(error)
        )
    }
    function addNewFriend(){
        FRIEND.currentuser=authContext.username
        FRIEND.amount=amount
        FRIEND.friend=newFriend
        addFriend(FRIEND).then(
            ()=>getList()
        ).catch(
            (error)=>console.log(error)
        )
    }
    function deleteFriend(id){
        removeFriend(id).then(
            ()=>getList()
        ).catch(
            (error)=>console.log(error)
        )
    }
    function navigateToAddBill(){
        navigateTo(`/add/bills`)
    }
    return(
        <div className="home-section">
            
            <ul className="list-friends">
                {
                    friendsList.map(element=>(
                    <div className="friend-row">
                        <li>{element.friend}</li>
                        <li>{element.amount}</li>
                        <li>{element.id}</li>
                        <button onClick={()=>deleteFriend(element.id)}>Delete</button>
                    </div>
                    ))
                }
            </ul>
            <div>
                <button onClick={addNewFriend}>Add Friend</button>
            </div>
            <div>
                <input type="text" value={newFriend} onChange={(e)=>setNewFriend(e.target.value)}/>
                <input type="number" value={amount} onChange={(e)=>setAmount(e.target.value)}/>
            </div>
            {friendsList.length>0?<MoneyChart data={friendsList}/>:""}
            <ExpensesPie></ExpensesPie>
            <Button variant="outlined" color="success" onClick={navigateToAddBill}>Add Bill</Button>
            
        </div>
    );
}

export default Welcome