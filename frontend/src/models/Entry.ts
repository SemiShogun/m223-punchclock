import { Category } from './Category';
import { Room } from './Room';

export interface Entry {
    checkIn: string;
    checkOut: string;
    room: Room;
    category: Category;
}