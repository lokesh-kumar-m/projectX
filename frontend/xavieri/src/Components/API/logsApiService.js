import { apiContext } from "./apiContext";

export const addNewLog=(LOG)=>apiContext.post(`/user/add/log`,LOG)