import {Component, Input, OnInit} from '@angular/core';
import {TaskStatus} from '../../dto/TaskStatus';

@Component({
	selector: 'app-task-status-icon',
	templateUrl: './task-status-icon.component.html',
	styleUrls: ['./task-status-icon.component.scss']
})
export class TaskStatusIconComponent implements OnInit {

	@Input()
	status: TaskStatus;

	constructor() {
	}

	ngOnInit() {
	}

}
