import {Component, Input, OnInit} from '@angular/core';
import {TimeService} from '../../../service/time.service';
import {Task} from '../../../dto/Task';
import {Project} from '../../../dto/Project';
import {AppComponent} from '../../../app.component';
import {ActivityService} from '../../../service/activity.service';
import {TokenProvider} from '../../../provider/token.provider';

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

	constructor(
		private app: AppComponent,
		private activityService: ActivityService,
		private tokenProvider: TokenProvider,
	) {
	}

	ngOnInit() {
		this.app.onLoad(() => {
			this.tokenProvider.token.subscribe(token => {
				this.tasks.forEach(t => {
					this.activityService.getLastByTask(token, t.id).subscribe(activity => {
						t.lastActivity = activity;
					}, error => {
						console.log('error handled');
						t.lastActivity = null;
					});
				});
			});
		});
	}

	formatDate(date: Date) {
		return TimeService.formatDate(date, 'MMMM Do[, ] YYYY');
	}

}
