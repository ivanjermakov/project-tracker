import {Component, Input, OnInit} from '@angular/core';
import {Project} from '../../../dto/Project';
import {Router} from '@angular/router';
import {User} from '../../../dto/User';

@Component({
	selector: 'app-feed-project',
	templateUrl: './feed-project.component.html',
	styleUrls: ['./feed-project.component.scss']
})
export class FeedProjectComponent implements OnInit {

	@Input()
	project: Project;

	@Input()
	me: User;

	constructor(
		private router: Router
	) {
	}

	ngOnInit() {
	}

	open() {
		this.router.navigate([this.project.user.login, this.project.name]);
	}
}
