import { Category } from './Category';
import { Room } from './Room';
import { User } from './User';

export interface Entry {
    checkIn: string;
    checkOut: string;
    room: Room;
    category: Category;
    user: User;
}