import {Component, Input, OnInit} from '@angular/core';
import {TaskType} from '../../../dto/TaskType';

@Component({
	selector: 'app-task-type-icon',
	templateUrl: './task-type-icon.component.html',
	styleUrls: ['./task-type-icon.component.scss']
})
export class TaskTypeIconComponent implements OnInit {

	@Input()
	type: TaskType;

	constructor() {
	}

	ngOnInit() {
	}

}
