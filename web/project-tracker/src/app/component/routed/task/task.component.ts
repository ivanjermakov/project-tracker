import {Component, OnInit} from '@angular/core';
import {Task} from '../../../dto/Task';
import {AppComponent} from '../../../app.component';
import {ActivatedRoute, Router} from '@angular/router';
import {UrlService} from '../../../service/url.service';
import {ProjectService} from '../../../service/project.service';
import {TaskService} from '../../../service/task.service';
import {AuthService} from '../../../service/auth.service';
import {User} from '../../../dto/User';
import {TimeService} from '../../../service/time.service';
import {Title} from '@angular/platform-browser';
import {EditTask} from '../../../dto/EditTask';
import {ActivityService} from '../../../service/activity.service';
import {Pageable} from '../../../dto/Pageable';
import {ACTIVITIES_IN_LIST} from '../../../../globals';
import {Activity} from '../../../dto/Activity';
import {TokenProvider} from '../../../provider/token.provider';

@Component({
	selector: 'app-task',
	templateUrl: './task.component.html',
	styleUrls: [
		'./task.component.scss',
		'./../../../app.component.scss',
		'./../../../style/edit.scss'
	]
})
export class TaskComponent implements OnInit {

	me: User;
	task: Task;
	beforeEditTask: Task;

	parentTask: Task;

	activities: Activity[];
	lastActivity: Activity;

	editMode: boolean = false;

	constructor(
		private app: AppComponent,
		private router: Router,
		private route: ActivatedRoute,
		private urlService: UrlService,
		private projectService: ProjectService,
		private taskService: TaskService,
		private activityService: ActivityService,
		private tokenProvider: TokenProvider,
		private authService: AuthService,
		private titleService: Title
	) {
	}

	ngOnInit() {
		this.app.onLoad(() => {
			console.debug('task initiation');
			this.route.params.subscribe(params => {
				console.debug('params', params);
				this.tokenProvider.token.subscribe(token => {
					this.authService.validate(token).subscribe(me => {
						this.me = me;
					});
					this.taskService.get(token, params['id']).subscribe(task => {
						this.task = task;
						console.debug(task);
						this.titleService.setTitle(`#${task.id} ${task.name}`);
						if (task.parentTaskId) {
							this.taskService.get(token, task.parentTaskId).subscribe(parentTask => {
								this.parentTask = parentTask;
							});
						}
						this.activityService.allByTask(token, this.task.id, new Pageable(0, ACTIVITIES_IN_LIST)).subscribe(activities => {
							this.activities = activities;
							this.lastActivity = activities[0];
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
		editTask.tag = this.task.tag;

		this.tokenProvider.token.subscribe(token => {
			this.taskService.edit(token, editTask).subscribe(task => {
				this.editMode = false;
				this.router.navigate([task.project.user.login, task.project.name, 'task', task.id]);
			});
		});
	}

	isPast(due: Date): boolean {
		return TimeService.isPast(due);
	}
}
