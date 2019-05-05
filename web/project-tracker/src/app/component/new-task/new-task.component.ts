import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {ProjectService} from '../../service/project.service';
import {TokenProviderService} from '../../service/token.provider.service';
import {AuthService} from '../../service/auth.service';
import {NewTask} from '../../dto/NewTask';
import {TaskService} from '../../service/task.service';
import {TaskType} from '../../dto/TaskType';
import {ArrayService} from '../../service/array.service';

@Component({
	selector: 'app-new-task',
	templateUrl: './new-task.component.html',
	styleUrls: [
		'./new-task.component.scss',
		'./../../app.component.scss'
	]
})
export class NewTaskComponent implements OnInit {

	task: NewTask = new NewTask();
	taskTypes: string[];

	constructor(
		private router: Router,
		private route: ActivatedRoute,
		private projectService: ProjectService,
		private taskService: TaskService,
		private tokenProviderService: TokenProviderService,
		private authService: AuthService,
		private arrayService: ArrayService
	) {
		// @ts-ignore
		this.task.type = TaskType[TaskType.FEATURE];
		this.arrayService.filter(Object.keys(TaskType), k => isNaN(parseInt(k)), keys => {
			this.taskTypes = keys;
			console.debug(this.taskTypes);
		});
	}

	ngOnInit() {
	}

	open() {
		this.tokenProviderService.token.subscribe(token => {
			this.route.params.subscribe(params => {
				console.debug('params', params);
				this.projectService.get(token, params['login'], params['name']).subscribe(project => {
					this.route.queryParams.subscribe(queryParams => {
						this.task.projectId = project.id;
						this.task.parentTaskId = queryParams['parentTaskId'] ? queryParams['parentTaskId'] : null;
						console.debug(this.task);
						this.taskService.create(token, this.task).subscribe(task => {
							this.router.navigate([`/${task.project.user.login}/${task.project.name}/task/${task.id}`]);
						});
					});
				});
			});
		});
	}

}
