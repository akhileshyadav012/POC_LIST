import axios from "axios";

const USER_API_BASE_URL = "http://localhost:9091/v1/orders/create";
const USER_UPDATE_API_BASE_URL = "http://localhost:9091/v1/orders/update";


class OrderService{

    createOrders(payment){
        return axios.post(USER_API_BASE_URL, payment);
    }
}

export default new OrderService()