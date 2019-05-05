import {Task} from './Task';
import {User} from './User';
import {TaskStatus} from './TaskStatus';

export class Activity {
	id: number;
	task: Task;
	creator: User;
	assignee: User;
	status: TaskStatus;
	description: string;
	elapsed: number;
	timestamp: Date;
}
