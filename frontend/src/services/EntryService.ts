import http from "../http";
import AuthHeader from "./AuthHeader";

class EntryService {

    getAll() {
        return http.get(`/entries`, { headers: AuthHeader() });
    }

    create(data: any) {
        return http.post(`/entries`, data, { headers: AuthHeader() });
    }

    update(id: string, data: any) {
        return http.put(`/entries/${id}`, data, { headers: AuthHeader() });
    }

    delete(id: string) {
        return http.delete(`/entries/${id}`, { headers: AuthHeader() });
    }
}

export default new EntryService();