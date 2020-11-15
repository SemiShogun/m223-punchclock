import http from "../http";
import AuthHeader from "./AuthHeader";

class CategoryService {

    getAll() {
        return http.get(`/categories`, { headers: AuthHeader() });
    }

    getById(id: number) {
        return http.get(`/categories/${id}`, { headers: AuthHeader() });
    }

    create(data: any) {
        return http.post(`/categories`, data, { headers: AuthHeader() });
    }

    update(id: string, data: any) {
        return http.put(`/categories/${id}`, data, { headers: AuthHeader() });
    }

    delete(id: string) {
        return http.delete(`/categories/${id}`, { headers: AuthHeader() });
    }
}

export default new CategoryService();