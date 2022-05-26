import {Component, OnInit} from '@angular/core';
import {User} from '../../../dto/User';
import {ActivatedRoute} from '@angular/router';
import {TokenProvider} from '../../../provider/token.provider';
import {UserService} from '../../../service/user.service';

@Component({
	selector: 'app-profile-followers',
	templateUrl: './profile-followers.component.html',
	styleUrls: [
		'./profile-followers.component.scss',
		'./../profile/profile.component.scss'
	]
})
export class ProfileFollowersComponent implements OnInit {

	followers: User[];

	constructor(
		private route: ActivatedRoute,
		private tokenProvider: TokenProvider,
		private userService: UserService,
	) {
	}

	ngOnInit() {
		this.route.params.subscribe(params => {
			this.tokenProvider.token.subscribe(token => {
				this.userService.getFollowers(token, params['login']).subscribe(followers => {
					this.followers = followers;
				});
			});
		});
	}

}
