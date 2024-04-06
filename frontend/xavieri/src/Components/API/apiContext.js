import axios from "axios";

export const apiContext=axios.create(
    {
        baseURL:'http://localhost:8080'
    }
)


export const fetchData = async () => {
    try {
        const data = await axios.get("https://api.exchangeratesapi.io/latest");
        return data.data.rates;
    } catch (e) {
        console.log(e);
    }
};
