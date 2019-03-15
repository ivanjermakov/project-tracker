import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {ProjectService} from '../../service/project.service';
import {TokenProviderService} from '../../service/token.provider.service';
import {Project} from '../../dto/Project';
import {UrlService} from '../../service/url.service';
import {AuthService} from '../../service/auth.service';

@Component({
	selector: 'app-project',
	templateUrl: './project.component.html',
	styleUrls: ['./project.component.scss']
})
export class ProjectComponent implements OnInit {

	project: Project;

	constructor(
		private router: Router,
		private route: ActivatedRoute,
		private urlService: UrlService,
		private projectService: ProjectService,
		private tokenProviderService: TokenProviderService,
		private authService: AuthService
	) {
	}

	ngOnInit() {
		this.route.params.subscribe(params => {
			console.log(params);
			this.tokenProviderService.token.subscribe(token => {
				this.projectService.get(token, params['login'], params['name']).subscribe(project => {
					this.project = project;
					console.log(project);
				});
			});
		});
	}

}
