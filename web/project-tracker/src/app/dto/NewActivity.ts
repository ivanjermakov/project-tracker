import {TaskStatus} from './TaskStatus';
import {TaskType} from './TaskType';

export class NewActivity {
	taskId: number;
	status: TaskStatus;
	type: TaskType;
	description: string;
	elapsed: number;
	assigneeLogin: string;
}
