import {Component, Input, OnInit} from '@angular/core';
import {TimeService} from '../../../service/time.service';
import {Task} from '../../../dto/Task';
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

	constructor(
		private app: AppComponent,
		private activityService: ActivityService,
		private tokenProvider: TokenProvider,
	) {
	}

	ngOnInit() {
	}

	formatDate(date: Date): string {
		return TimeService.formatDate(date, 'MMMM Do[, ] YYYY');
	}

	isPast(due: Date): boolean {
		return TimeService.isPast(due);
	}
}
