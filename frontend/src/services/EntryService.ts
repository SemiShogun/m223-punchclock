import http from "../http";
import AuthHeader from "./AuthHeader";

class EntryService {

    getAll() {
        return http.get(`/entries`, { headers: AuthHeader() });
    }

    getById(id: number) {
        return http.get(`/entries/${id}`, { headers: AuthHeader() });
    }

    create(data: any) {
        return http.post(`/entries`, data, { headers: AuthHeader() });
    }

    update(id: number, data: any) {
        return http.put(`/entries/${id}`, data, { headers: AuthHeader() });
    }

    delete(id: number) {
        return http.delete(`/entries/${id}`, { headers: AuthHeader() });
    }
}

export default new EntryService();