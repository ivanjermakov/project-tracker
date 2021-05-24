import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {TokenProvider} from '../../../provider/token.provider';
import {AuthService} from '../../../service/auth.service';
import {ArrayService} from '../../../service/array.service';
import {NewActivity} from '../../../dto/NewActivity';
import {TaskStatus} from '../../../dto/TaskStatus';
import {ActivityService} from '../../../service/activity.service';
import {UserService} from '../../../service/user.service';

@Component({
	selector: 'app-new-activity',
	templateUrl: './new-activity.component.html',
	styleUrls: [
		'./new-activity.component.scss',
		'./../../../app.component.scss'
	]
})
export class NewActivityComponent implements OnInit {

	activity: NewActivity = new NewActivity();
	taskStatuses: string[];

	query: string;
	suggestions: any[];

	constructor(
		private router: Router,
		private route: ActivatedRoute,
		private activityService: ActivityService,
		private tokenProvider: TokenProvider,
		private authService: AuthService,
		private arrayService: ArrayService,
		private userService: UserService,
	) {
		// @ts-ignore
		this.activity.status = TaskStatus[TaskStatus.OPEN];
		this.arrayService.filter(Object.keys(TaskStatus), k => isNaN(parseInt(k)), keys => this.taskStatuses = keys);
	}

	ngOnInit() {
	}

	create() {
		this.tokenProvider.token.subscribe(token => {
			this.route.params.subscribe(params => {
				console.debug('params', params);
				this.activity.taskId = params['taskId'];
				this.activityService.create(token, this.activity).subscribe(activity => {
					this.router.navigate(
						[`/${activity.task.project.user.login}/${activity.task.project.name}/task/${activity.task.id}`]
					);
				});
			});
		});
	}

	search() {
		this.tokenProvider.token.subscribe(token => {
			this.userService.find(token, this.query).subscribe(suggestions => {
				this.suggestions = suggestions;
				this.suggestions.forEach(s => {
					s.skillList = s.userInfo.skills?.split(' ');
				});
			});
		});
	}

	assignMember(user: any) {
		this.activity.assigneeLogin = user.login;
		this.query = user.login;
		this.suggestions = undefined;
	}

}
