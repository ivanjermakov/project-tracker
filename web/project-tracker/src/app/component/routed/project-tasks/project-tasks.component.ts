import {Component, OnInit} from '@angular/core';
import {Pageable} from '../../../dto/Pageable';
import {TASKS_IN_PROJECT} from '../../../../globals';
import {AppComponent} from '../../../app.component';
import {ActivatedRoute, Router} from '@angular/router';
import {UrlService} from '../../../service/url.service';
import {ProjectService} from '../../../service/project.service';
import {TaskService} from '../../../service/task.service';
import {TokenProvider} from '../../../provider/token.provider';
import {AuthService} from '../../../service/auth.service';
import {Title} from '@angular/platform-browser';
import {ArrayService} from '../../../service/array.service';
import {Project} from '../../../dto/Project';
import {User} from '../../../dto/User';
import {Task} from '../../../dto/Task';
import {ListTask} from '../../../dto/ListTask';
import {TaskStatus} from '../../../dto/TaskStatus';
import {TaskType} from '../../../dto/TaskType';
import {TaskPriority} from '../../../dto/TaskPriority';

@Component({
	selector: 'app-project-tasks',
	templateUrl: './project-tasks.component.html',
	styleUrls: [
		'./project-tasks.component.scss',
		'./../../../app.component.scss',
		'./../../../style/edit.scss'
	]
})
export class ProjectTasksComponent implements OnInit {

	project: Project;
	me: User;
	tasks: Task[];
	listTask: ListTask = new ListTask();
	token: string;

	taskStatuses: string[];
	taskTypes: string[];
	taskPriorities: string[];

	constructor(
		private app: AppComponent,
		private router: Router,
		private route: ActivatedRoute,
		private urlService: UrlService,
		private projectService: ProjectService,
		private taskService: TaskService,
		private tokenProvider: TokenProvider,
		private authService: AuthService,
		private titleService: Title,
		private arrayService: ArrayService
	) {
		this.arrayService.filter(Object.keys(TaskStatus), k => isNaN(parseInt(k)), keys => this.taskStatuses = ['', ...keys]);
		this.arrayService.filter(Object.keys(TaskType), k => isNaN(parseInt(k)), keys => this.taskTypes = ['', ...keys]);
		this.arrayService.filter(Object.keys(TaskPriority), k => isNaN(parseInt(k)), keys => this.taskPriorities = ['', ...keys]);
	}

	ngOnInit(): void {
		this.app.onLoad(() => {
			console.debug('project initiation');
			this.route.params.subscribe(params => {
				console.debug('params', params);
				this.tokenProvider.token.subscribe(token => {
					this.token = token;
					this.authService.validate(token).subscribe(me => {
						this.me = me;
						this.projectService.get(token, params['login'], params['name']).subscribe(project => {
							this.project = project;
							this.titleService.setTitle(this.project.name);
							this.updateList();
						});
					});
				});
			});
		});
	}

	updateList() {
		this.listTask.status = this.listTask.status as any === '' ? null : this.listTask.status
		this.listTask.type = this.listTask.type as any === '' ? null : this.listTask.type
		this.listTask.priority = this.listTask.priority as any === '' ? null : this.listTask.priority
		this.taskService.list(this.token, this.project.id, this.listTask, new Pageable(0, TASKS_IN_PROJECT)).subscribe(tasks => {
			this.tasks = tasks;
		});
	}

	change() {
		this.updateList();
	}
}
