import {Component, Input, OnInit} from '@angular/core';
import {TaskPriority} from '../../../dto/TaskPriority';

@Component({
	selector: 'app-task-priority-icon',
	templateUrl: './task-priority-icon.component.html',
	styleUrls: ['./task-priority-icon.component.scss']
})
export class TaskPriorityIconComponent implements OnInit {

	@Input()
	priority: TaskPriority;

	constructor() {
	}

	ngOnInit() {
	}

}
