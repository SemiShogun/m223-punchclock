import { User } from '@/interfaces/User';
import http from "../http";
import AuthHeader from "./AuthHeader";

class AuthService {
  login(username: string, password: string) {
    return http.post(`/login`, { username, password }, { headers: AuthHeader() }).then((res) => {
      localStorage.setItem(
        "bearer",
        JSON.stringify(res.headers["authorization"].split("Bearer ")[1])
      );
    });
  }

  logout() {
    localStorage.removeItem("bearer");
  }

  register(username: string, password: string) {
    return http.post(`/users/sign-up`, { username, password });
  }

  getCurrentUser() {
    return http.get(`/users/currentUser`, { headers: AuthHeader() });
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
