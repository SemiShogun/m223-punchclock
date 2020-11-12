import { Entry } from './Entry';

export interface User {
    username: string;
    password: string;
    entries: Array<Entry>;
}