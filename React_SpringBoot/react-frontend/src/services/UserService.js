import axios from "axios";

const USER_API_BASE_URL = "http://localhost:8081/v1/users";

class UserService{

    getEmployees(){
        return axios.get(USER_API_BASE_URL);
    }

    createUser(user){
        return axios.post(USER_API_BASE_URL, user);
    }

    getUserById(userId){
        return axios.get(USER_API_BASE_URL + "/" + userId);
    }

    updateUserById(user, userId){
        return axios.put(USER_API_BASE_URL + "/" + userId, user)
    }

    deleteUserById(userId){
        return axios.delete(USER_API_BASE_URL + "/" + userId)
    }
}

export default new UserService()