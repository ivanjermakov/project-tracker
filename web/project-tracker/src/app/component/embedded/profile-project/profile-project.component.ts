import {Component, Input, OnInit} from '@angular/core';
import {Project} from '../../../dto/Project';
import {Router} from '@angular/router';

@Component({
	selector: 'app-profile-project',
	templateUrl: './profile-project.component.html',
	styleUrls: ['./profile-project.component.scss']
})
export class ProfileProjectComponent implements OnInit {

	@Input()
	project: Project;

	constructor(
		private router: Router
	) {
	}

	ngOnInit(): void {
	}

	open() {
		this.router.navigate([this.project.user.login, this.project.name]);
	}
}
