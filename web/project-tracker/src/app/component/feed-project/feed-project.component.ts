import {Component, Input, OnInit} from '@angular/core';
import {Project} from '../../dto/Project';
import {Router} from '@angular/router';
import {AuthService} from '../../service/auth.service';
import {TokenProviderService} from '../../service/token.provider.service';
import {User} from '../../dto/User';

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
		private tokenProviderService: TokenProviderService,
		private authService: AuthService
	) {
	}

	ngOnInit() {
		console.debug('p: ', this.project);
	}

	open() {
		this.tokenProviderService.token.subscribe(token => {
			this.authService.validate(token).subscribe(user => {
				this.router.navigate([user.login, this.project.name]);
			});
		});
	}
}
