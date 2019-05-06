import {Component, OnInit} from '@angular/core';
import {NewProject} from '../../../dto/NewProject';
import {ProjectService} from '../../../service/project.service';
import {TokenProviderService} from '../../../service/token.provider.service';
import {AuthService} from '../../../service/auth.service';
import {Router} from '@angular/router';

@Component({
	selector: 'app-new-project',
	templateUrl: './new-project.component.html',
	styleUrls: [
		'./new-project.component.scss',
		'./../../../app.component.scss'
	]
})
export class NewProjectComponent implements OnInit {

	project: NewProject;

	constructor(
		private router: Router,
		private projectService: ProjectService,
		private tokenProviderService: TokenProviderService,
		private authService: AuthService
	) {
		this.project = new NewProject();
		this.project.isPublic = true;
	}

	ngOnInit() {
	}

	create() {
		this.tokenProviderService.token.subscribe(token => {
			console.debug(this.project);
			this.projectService.create(token, this.project).subscribe(project => {
				console.debug('created project', project);
				this.authService.validate(token).subscribe(user => {
					this.router.navigate([user.login, project.name]);
				});
			});
		});
	}

}
