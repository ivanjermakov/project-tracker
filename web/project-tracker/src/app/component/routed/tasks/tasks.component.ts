import {Component, OnInit} from '@angular/core';
import {AppComponent} from '../../../app.component';
import {TokenProvider} from '../../../provider/token.provider';
import {TaskService} from '../../../service/task.service';
import {Pageable} from '../../../dto/Pageable';
import {ASSIGNEE_TASKS_IN_TASKS, OWNED_TASKS_IN_TASKS} from '../../../../globals';
import {Direction} from '../../../dto/SortDirection';
import {Task} from '../../../dto/Task';

@Component({
	selector: 'app-tasks',
	templateUrl: './tasks.component.html',
	styleUrls: [
		'./tasks.component.scss',
		'./../../../app.component.scss'
	]
})
export class TasksComponent implements OnInit {
	ownedTasks: Task[];
	assigneeTasks: Task[];

	constructor(
		private app: AppComponent,
		private tokenProvider: TokenProvider,
		private taskService: TaskService
	) {
	}

	ngOnInit() {
		this.app.onLoad(() => {
			this.tokenProvider.token.subscribe(token => {
				this.taskService.owned(
					token,
					new Pageable(0, OWNED_TASKS_IN_TASKS, 'opened', Direction.DESC)
				).subscribe(tasks => {
					this.ownedTasks = tasks;
				});
				this.taskService.assignee(
					token,
					new Pageable(0, ASSIGNEE_TASKS_IN_TASKS, 'opened', Direction.DESC)
				).subscribe(tasks => {
					this.assigneeTasks = tasks;
				});
			});
		});
	}

}
