import {Project} from './Project';
import {User} from './User';
import {TaskType} from './TaskType';
import {Activity} from './Activity';
import {TaskStatus} from './TaskStatus';
import {TaskPriority} from './TaskPriority';

export class Task {
	id: number;
	project: Project;
	parentTaskId: number;
	creator: User;
	type: TaskType;
	status: TaskStatus;
	priority: TaskPriority;
	tag: string;
	estimate: number;
	elapsed: number;
	opened: Date;
	started: Date;
	due: Date;
	name: string;
	description: string;
	subtasks: Task[];

	lastActivity: Activity;
	assignee: User;
}
