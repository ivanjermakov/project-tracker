import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {ProjectService} from '../../service/project.service';
import {TokenProviderService} from '../../service/token.provider.service';
import {Project} from '../../dto/Project';
import {UrlService} from '../../service/url.service';
import {UserProviderService} from '../../service/user.provider.service';

import * as moment from 'moment';
import {EditProject} from '../../dto/EditProject';
import {AuthService} from '../../service/auth.service';
import {AppComponent} from '../../app.component';
import {User} from '../../dto/User';

@Component({
	selector: 'app-project',
	templateUrl: './project.component.html',
	styleUrls: [
		'./project.component.scss',
		'./../../app.component.scss',
	]
})
export class ProjectComponent implements OnInit {

	project: Project;
	beforeEditProject: Project;
	me: User;
	mine: boolean;
	editMode: boolean = false;

	constructor(
		private app: AppComponent,
		private router: Router,
		private route: ActivatedRoute,
		private urlService: UrlService,
		private projectService: ProjectService,
		private tokenProviderService: TokenProviderService,
		private userProviderService: UserProviderService,
		private authService: AuthService
	) {
	}

	ngOnInit() {
		this.app.onLoad(() => {
			this.userProviderService.me.subscribe(me => {
				this.me = me;
			});
			console.debug('project initiation');
			this.userProviderService.me.subscribe(me => {
				this.route.params.subscribe(params => {
					console.debug('params', params);
					this.tokenProviderService.token.subscribe(token => {
						this.projectService.get(token, params['login'], params['name']).subscribe(project => {
							this.project = project;
							console.debug(project);
							this.mine = project.user.id === me.id;
							console.debug('mine: ', this.mine);
						});
					});
				});
			});
		});
	}

	formatDate(date: Date) {
		return moment(date).format('MMMM Do[, ] YYYY [ at ] HH:mm');
	}

	edit() {
		this.beforeEditProject = JSON.parse(JSON.stringify(this.project));
		this.editMode = true;
	}

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

		this.tokenProviderService.token.subscribe(token => {
			this.projectService.edit(token, editProject).subscribe(project => {
				this.editMode = false;
				this.authService.validate(token).subscribe(user => {
					this.router.navigate([user.login, project.name]);
				});
			});
		});
	}
}
