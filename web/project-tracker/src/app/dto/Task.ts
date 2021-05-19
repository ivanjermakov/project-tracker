import {Project} from './Project';
import {User} from './User';
import {TaskType} from './TaskType';
import {Activity} from './Activity';

export class Task {
	id: number;
	project: Project;
	parentTaskId: number;
	creator: User;
	type: TaskType;
	estimate: number;
	elapsed: number;
	opened: Date;
	due: Date;
	name: string;
	description: string;
	subtasks: Task[];

	lastActivity: Activity;
	assignee: User;
}
