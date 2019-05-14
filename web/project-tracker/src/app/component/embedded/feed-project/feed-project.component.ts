import {Component, Input, OnInit} from '@angular/core';
import {Project} from '../../../dto/Project';
import {Router} from '@angular/router';
import {AuthService} from '../../../service/auth.service';
import {User} from '../../../dto/User';
import {TokenProvider} from '../../../provider/token.provider';

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
		private router: Router,
		private tokenProvider: TokenProvider,
		private authService: AuthService
	) {
	}

	ngOnInit() {
	}

	open() {
		this.tokenProvider.token.subscribe(token => {
			this.authService.validate(token).subscribe(user => {
				this.router.navigate([user.login, this.project.name]);
			});
		});
	}
}
