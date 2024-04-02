import { apiContext } from "./apiContext";

export const getMyList=(currentUser)=>apiContext.get(`/home/${currentUser}`)

// /home/newfriend
export const addFriend=(FRIEND)=>apiContext.post(`/home/newfriend`,FRIEND)