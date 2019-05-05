import {Component, Input, OnInit} from '@angular/core';
import {TimeService} from '../../service/time.service';
import {Task} from '../../dto/Task';
import {Project} from '../../dto/Project';

@Component({
	selector: 'app-tasks-table',
	templateUrl: './tasks-table.component.html',
	styleUrls: ['./tasks-table.component.scss']
})
export class TasksTableComponent implements OnInit {

	@Input()
	tasks: Task[];
	@Input()
	project: Project;

	constructor() {
	}

	ngOnInit() {
	}

	formatDate(date: Date) {
		return TimeService.formatDate(date, 'MMMM Do[, ] YYYY');
	}

}
