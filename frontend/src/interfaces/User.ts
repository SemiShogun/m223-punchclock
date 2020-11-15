import { Entry } from './Entry';

export interface User {
    username: string;
    password: string;
    role?: string;
    entries?: Array<Entry>;
}