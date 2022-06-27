import axios from 'axios';

const BASE_URL = "http://localhost:5050/"

export const signIn = async (params: any) => {
    const response = await axios.post(`${BASE_URL}identity/signin`, params)
    return response
}

