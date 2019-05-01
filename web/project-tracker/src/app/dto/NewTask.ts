import {TaskType} from './TaskType';

export class NewTask {
	parentTaskId: number;
	projectId: number;
	type: TaskType;
	estimate: number;
	due: Date;
	name: string;
	description: string;
}
