import {TaskStatus} from './TaskStatus';

export class NewActivity {
	taskId: number;
	status: TaskStatus;
	description: string;
	elapsed: number;
	assigneeLogin: string;
}
