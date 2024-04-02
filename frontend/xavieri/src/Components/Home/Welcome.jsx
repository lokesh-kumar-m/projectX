import { useEffect, useState } from "react";
import { useAuth } from "../auth";
import { getMyList,addFriend } from "../API/friendsApiService";

import './home.css'

const FRIEND={
    currentuser:"",
    friend:"",
    amount:0
}
const Welcome=()=>{
    const authContext=useAuth()
    const [friendsList,setList]=useState([])
    const [newFriend,setNewFriend]=useState('')
    const [amount, setAmount]=useState(0)
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
    return(
        <div className="home-section">
            <ul className="list-friends">
                {
                    friendsList.map(element=>(
                    <div className="friend-row">
                        <li>{element.friend}</li>
                        <li>{element.amount}</li>
                        <li>{element.id}</li>
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

        </div>
    );
}

export default Welcome