import http from "../http";
import AuthHeader from "./AuthHeader";

class RoomService {

    getAll() {
        return http.get(`/rooms`, { headers: AuthHeader() });
    }

    create(data: any) {
        return http.post(`/rooms`, data, { headers: AuthHeader() });
    }

    update(id: string, data: any) {
        return http.put(`/rooms/${id}`, data, { headers: AuthHeader() });
    }

    delete(id: string) {
        return http.delete(`/rooms/${id}`, { headers: AuthHeader() });
    }
}

export default new RoomService();