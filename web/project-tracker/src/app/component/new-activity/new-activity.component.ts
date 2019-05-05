import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {TokenProviderService} from '../../service/token.provider.service';
import {AuthService} from '../../service/auth.service';
import {ArrayService} from '../../service/array.service';
import {NewActivity} from '../../dto/NewActivity';
import {TaskStatus} from '../../dto/TaskStatus';
import {ActivityService} from '../../service/activity.service';

@Component({
	selector: 'app-new-activity',
	templateUrl: './new-activity.component.html',
	styleUrls: [
		'./new-activity.component.scss',
		'./../../app.component.scss'
	]
})
export class NewActivityComponent implements OnInit {

	activity: NewActivity = new NewActivity();
	taskStatuses: string[];

	constructor(
		private router: Router,
		private route: ActivatedRoute,
		private activityService: ActivityService,
		private tokenProviderService: TokenProviderService,
		private authService: AuthService,
		private arrayService: ArrayService
	) {
		// @ts-ignore
		this.activity.status = TaskStatus[TaskStatus.OPEN];
		this.arrayService.filter(Object.keys(TaskStatus), k => isNaN(parseInt(k)), keys => {
			this.taskStatuses = keys;
			console.debug(this.taskStatuses);
		});
	}

	ngOnInit() {
	}

	create() {
		this.tokenProviderService.token.subscribe(token => {
			this.route.params.subscribe(params => {
				console.debug('params', params);
				this.activity.taskId = params['taskId'];
				this.activityService.create(token, this.activity).subscribe(activity => {
					this.router.navigate(
						[`/${activity.task.project.user.login}/${activity.task.project.name}/task/${activity.task.id}/activity/${activity.id}`]
					);
				});
			});
		});
	}

}
