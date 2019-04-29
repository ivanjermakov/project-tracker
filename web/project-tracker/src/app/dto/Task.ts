import {Project} from './Project';
import {User} from './User';
import {TaskType} from './TaskType';

export class Task {
	id: number;
	project: Project;
	creator: User;
	type: TaskType;
	estimate: number;
	elapsed: number;
	opened: Date;
	due: Date;
	name: string;
	description: string;
	subtasks: Task[];
}
