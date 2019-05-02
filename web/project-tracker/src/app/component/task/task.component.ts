import {Component, OnInit} from '@angular/core';
import {Task} from '../../dto/Task';
import {AppComponent} from '../../app.component';
import {Pageable} from '../../dto/Pageable';
import {TASKS_IN_TABLE} from '../../../globals';
import {ActivatedRoute, Router} from '@angular/router';
import {UrlService} from '../../service/url.service';
import {ProjectService} from '../../service/project.service';
import {TaskService} from '../../service/task.service';
import {TokenProviderService} from '../../service/token.provider.service';
import {UserProviderService} from '../../service/user.provider.service';
import {AuthService} from '../../service/auth.service';
import {User} from '../../dto/User';

@Component({
	selector: 'app-task',
	templateUrl: './task.component.html',
	styleUrls: [
		'./task.component.scss',
		'./../../app.component.scss'
	]
})
export class TaskComponent implements OnInit {

	me: User;
	task: Task;

	constructor(
		private app: AppComponent,
		private router: Router,
		private route: ActivatedRoute,
		private urlService: UrlService,
		private projectService: ProjectService,
		private taskService: TaskService,
		private tokenProviderService: TokenProviderService,
		private userProviderService: UserProviderService,
		private authService: AuthService
	) {
	}


	ngOnInit() {
		this.app.onLoad(() => {
			console.debug('task initiation');
			this.userProviderService.me.subscribe(me => {
				this.me = me;
				this.route.params.subscribe(params => {
					console.debug('params', params);
					this.tokenProviderService.token.subscribe(token => {
						this.taskService.get(token, params["id"]).subscribe(task => {
							this.task = task;
							console.debug(task);
						});
					});
				});
			});
		});
	}

}
