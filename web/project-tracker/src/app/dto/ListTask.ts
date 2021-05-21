import {TaskStatus} from './TaskStatus';
import {TaskType} from './TaskType';
import {TaskPriority} from './TaskPriority';

export class ListTask {
	status: TaskStatus;
	type: TaskType;
	priority: TaskPriority;
	assignee: string;
	name: string;
	tag: string;
}
