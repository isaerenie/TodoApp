import axios from "axios";
// import { control } from "../utils/control";
 

const baseUrl = 'http://localhost:3333/todo/'
export const axiosConfig = axios.create({
    baseURL: baseUrl,
})


// export const siteConfig = axios.create({
//     baseURL: baseUrl,
//     timeout: timeout,
//    headers: { 'Authorization': 'Bearer ' + control()?.jwt }
// }) 