import { User } from "@/models/User";
import http from "../http";
import AuthHeader from "./AuthHeader";

class AuthService {
  login(user: User) {
    return http.post(`/login`, user, { headers: AuthHeader() }).then((res) => {
      console.log(res.headers);
      localStorage.setItem(
        "bearer",
        JSON.stringify(res.headers["authorization"].split("Bearer ")[1])
      );
      console.log("pass");
      console.log(localStorage.getItem("bearer"));
    });
  }

  logout() {
    localStorage.removeItem("bearer");
  }

  register(user: User) {
    return http.post(`/users/sign-up`, user, { headers: AuthHeader() });
  }

  getRole() {
    return http.get(`/users/role`, { headers: AuthHeader() });
  }

  getUsername() {
    return http.get(`/users/username`, { headers: AuthHeader() });
  }

  getAllUsers() {
    return http.get(`/users/allUsers`, { headers: AuthHeader() });
  }

  createUser(user: User) {
    return http.post(`/users`, user, { headers: AuthHeader() });
  }

  deleteUser(id: number) {
    return http.delete(`/users/${id}`, { headers: AuthHeader() });
  }

  updateUser(id: number, user: User) {
    return http.put(`/users/${id}`, user, { headers: AuthHeader() });
  }
}

export default new AuthService();
