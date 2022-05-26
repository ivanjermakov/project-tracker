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
	started: Date;
	due: Date;

	fullName: string;
	birthDate: Date;
	height: number;
	weight: number;
	medicalHistory: string;
	tookMedicine: boolean;

	name: string;
	description: string;
}
