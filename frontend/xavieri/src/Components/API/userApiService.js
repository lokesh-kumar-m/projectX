import { apiContext } from "./apiContext";

export const AddNewUser=(USER)=>apiContext.post(`/users/register`,USER)