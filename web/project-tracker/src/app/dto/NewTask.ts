import {TaskType} from './TaskType';
import {TaskStatus} from './TaskStatus';
import {TaskPriority} from './TaskPriority';

export class NewTask {
	parentTaskId: number;
	projectId: number;
	type: TaskType;
	status: TaskStatus;
	priority: TaskPriority;
	estimate: number;
	due: Date;
	name: string;
	description: string;
}
