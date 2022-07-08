import axios from 'axios';

const BASE_URL = "https://notimed-api.me/"

export const signIn = async (params: any) => {
    const response = await axios.post(`${BASE_URL}identity/signin`, params)
    return response
}


export const forgotPassword = async(params:any) =>{
    const response = await axios.post(`${BASE_URL}identity/forgot-password`, params);
    return response;
}
