import {Component, OnInit} from '@angular/core';
import {Task} from '../../dto/Task';
import {AppComponent} from '../../app.component';
import {ActivatedRoute, Router} from '@angular/router';
import {UrlService} from '../../service/url.service';
import {ProjectService} from '../../service/project.service';
import {TaskService} from '../../service/task.service';
import {TokenProviderService} from '../../service/token.provider.service';
import {UserProviderService} from '../../service/user.provider.service';
import {AuthService} from '../../service/auth.service';
import {User} from '../../dto/User';
import {TimeService} from '../../service/time.service';
import {Title} from '@angular/platform-browser';
import {EditTask} from '../../dto/EditTask';

@Component({
	selector: 'app-task',
	templateUrl: './task.component.html',
	styleUrls: [
		'./task.component.scss',
		'./../../app.component.scss',
		'./../../style/edit.scss'
	]
})
export class TaskComponent implements OnInit {

	me: User;
	task: Task;
	beforeEditTask: Task;
	editMode: boolean = false;

	constructor(
		private app: AppComponent,
		private router: Router,
		private route: ActivatedRoute,
		private urlService: UrlService,
		private projectService: ProjectService,
		private taskService: TaskService,
		private tokenProviderService: TokenProviderService,
		private userProviderService: UserProviderService,
		private authService: AuthService,
		private titleService: Title
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
						this.taskService.get(token, params['id']).subscribe(task => {
							this.task = task;
							console.debug(task);
							this.titleService.setTitle(`#${task.id} ${task.name}`);
						});
					});
				});
			});
		});
	}

	formatDateWithTime(date: Date) {
		return TimeService.formatDate(date, 'MMMM Do[, ] YYYY [ at ] HH:mm');
	}

	edit() {
		this.beforeEditTask = JSON.parse(JSON.stringify(this.task));
		this.editMode = true;
	}

	// TODO: keystrokes support
	cancelEdit() {
		this.editMode = false;
		this.task = this.beforeEditTask;
		this.beforeEditTask = null;
	}

	applyEdit() {
		let editTask = new EditTask();
		editTask.id = this.task.id;
		editTask.name = this.task.name;
		editTask.description = this.task.description;

		this.tokenProviderService.token.subscribe(token => {
			this.taskService.edit(token, editTask).subscribe(task => {
				this.editMode = false;
				this.router.navigate([task.project.user.login, task.project.name, 'task', task.id]);
			});
		});
	}
}
