import {Component, OnInit} from '@angular/core';
import {Project} from '../../../dto/Project';
import {ProjectService} from '../../../service/project.service';
import {TokenProvider} from '../../../provider/token.provider';
import {Pageable} from '../../../dto/Pageable';
import {PROJECTS_IN_PROFILE} from '../../../../globals';
import {ActivatedRoute} from '@angular/router';

@Component({
	selector: 'app-profile-projects',
	templateUrl: './profile-projects.component.html',
	styleUrls: ['./profile-projects.component.scss']
})
export class ProfileProjectsComponent implements OnInit {

	projects: Project[] = [];

	constructor(
		private route: ActivatedRoute,
		private tokenProvider: TokenProvider,
		private projectService: ProjectService
	) {
	}

	ngOnInit(): void {
		this.route.params.subscribe(params => {
			this.tokenProvider.token.subscribe(token => {
				this.projectService.userAll(token, params['login'], new Pageable(0, PROJECTS_IN_PROFILE)).subscribe(projects =>
					this.projects = projects
				);
			});
		});
	}

}
