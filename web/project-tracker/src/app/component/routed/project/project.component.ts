import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {ProjectService} from '../../../service/project.service';
import {Project} from '../../../dto/Project';
import {UrlService} from '../../../service/url.service';

import {EditProject} from '../../../dto/EditProject';
import {AuthService} from '../../../service/auth.service';
import {AppComponent} from '../../../app.component';
import {User} from '../../../dto/User';
import {TimeService} from '../../../service/time.service';
import {Task} from '../../../dto/Task';
import {Pageable} from '../../../dto/Pageable';
import {TASKS_IN_TABLE} from '../../../../globals';
import {TaskService} from '../../../service/task.service';
import {Title} from '@angular/platform-browser';
import {ArrayService} from '../../../service/array.service';
import {UserProvider} from '../../../provider/user.provider';
import {TokenProvider} from '../../../provider/token.provider';
import {ErrorService} from '../../../service/error.service';

@Component({
	selector: 'app-project',
	templateUrl: './project.component.html',
	styleUrls: [
		'./project.component.scss',
		'./../../../app.component.scss',
		'./../../../style/edit.scss'
	]
})
export class ProjectComponent implements OnInit {

	project: Project;
	beforeEditProject: Project;
	me: User;
	mine: boolean;
	editMode: boolean = false;
	tasks: Task[];

	constructor(
		private app: AppComponent,
		private router: Router,
		private route: ActivatedRoute,
		private urlService: UrlService,
		private projectService: ProjectService,
		private taskService: TaskService,
		private tokenProvider: TokenProvider,
		private userProvider: UserProvider,
		private authService: AuthService,
		private titleService: Title,
		private arrayService: ArrayService,
		private errorService: ErrorService
	) {
	}

	ngOnInit() {
		this.app.onLoad(() => {
			console.debug('project initiation');
			this.userProvider.me.subscribe(me => {
				this.me = me;
				this.route.params.subscribe(params => {
					console.debug('params', params);
					this.tokenProvider.token.subscribe(token => {
						this.projectService.get(token, params['login'], params['name']).subscribe(project => {
							this.project = project;
							console.debug('project: ', this.project);
							this.mine = this.project.user.id === this.me.id;
							this.titleService.setTitle(this.project.name);
							this.taskService.all(token, project.id, new Pageable(0, TASKS_IN_TABLE)).subscribe(tasks => {
								// show only primary tasks (that does not have parent task above)
								this.arrayService.filter(tasks, t => !t.parentTaskId, tasks => {
									this.tasks = tasks;
									console.debug(this.tasks);
								});
							});
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
		this.beforeEditProject = JSON.parse(JSON.stringify(this.project));
		this.editMode = true;
	}

	// TODO: keystrokes support
	cancelEdit() {
		this.editMode = false;
		this.project = this.beforeEditProject;
		this.beforeEditProject = null;
	}

	applyEdit() {
		let editProject = new EditProject();
		editProject.id = this.project.id;
		editProject.isPublic = this.project.isPublic;
		editProject.name = this.project.name;
		editProject.description = this.project.description;
		editProject.about = this.project.about;

		this.tokenProvider.token.subscribe(token => {
			this.projectService.edit(token, editProject).subscribe(project => {
				this.editMode = false;
				this.router.navigate([project.user.login, project.name]);
			}, e => this.errorService.raise(e));
		});
	}
}
