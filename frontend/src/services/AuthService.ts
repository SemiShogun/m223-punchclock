import axios from "axios";

const staticURL = 'http://localhost:8081';

class AuthService {

    login(username: string, password: string) {
        const credentials = JSON.stringify({ username, password });
        return axios.post(`${staticURL}/login`, { username, password }, {
            headers: {
                'content-type': 'text/json'
            }
        })
        .then(res => {
            console.log(res.headers);
            localStorage.setItem('bearer', JSON.stringify(res.headers['authorization'].split('Bearer ')[1]));
            console.log("pass");
            console.log(localStorage.getItem('bearer'));
        });
    }

    logout() {
        localStorage.removeItem('bearer');
    }

    register(username: string, password: string) {
        return axios.post(`${staticURL}/users/sign-up`, {
            username,
            password
        });
    }
}

export default new AuthService();