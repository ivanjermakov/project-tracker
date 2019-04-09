import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {ProjectService} from '../../service/project.service';
import {TokenProviderService} from '../../service/token.provider.service';
import {Project} from '../../dto/Project';
import {UrlService} from '../../service/url.service';
import {UserProviderService} from '../../service/user.provider.service';
import {User} from '../../dto/User';

import * as moment from 'moment';

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
	me: User;

	constructor(
		private router: Router,
		private route: ActivatedRoute,
		private urlService: UrlService,
		private projectService: ProjectService,
		private tokenProviderService: TokenProviderService,
		private userProviderService: UserProviderService,
	) {
	}

	ngOnInit() {
		console.debug('project initiation');
		this.userProviderService.me.subscribe(me => {
			this.me = me;
		});
		this.route.params.subscribe(params => {
			console.debug('params', params);
			this.tokenProviderService.token.subscribe(token => {
				this.projectService.get(token, params['login'], params['name']).subscribe(project => {
					this.project = project;
					console.debug(project);
				});
			});
		});
	}

	formatDate(date: Date) {
		return moment(date).format('MMMM Do[, ] YYYY [ at ] HH:mm');
	}

}
