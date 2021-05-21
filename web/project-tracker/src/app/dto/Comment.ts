import {User} from './User';
import {Task} from './Task';

export class Comment {
	id: number;
	task: Task;
	creator: User;
	text: string;
	timestamp: Date;
}
