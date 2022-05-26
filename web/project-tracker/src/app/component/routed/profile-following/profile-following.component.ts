import {Component, OnInit} from '@angular/core';
import {User} from '../../../dto/User';
import {ActivatedRoute} from '@angular/router';
import {TokenProvider} from '../../../provider/token.provider';
import {UserService} from '../../../service/user.service';

@Component({
	selector: 'app-profile-following',
	templateUrl: './profile-following.component.html',
	styleUrls: [
		'./profile-following.component.scss',
		'./../profile/profile.component.scss'
	]
})
export class ProfileFollowingComponent implements OnInit {

	following: User[];

	constructor(
		private route: ActivatedRoute,
		private tokenProvider: TokenProvider,
		private userService: UserService,
	) {
	}

	ngOnInit() {
		this.route.params.subscribe(params => {
			this.tokenProvider.token.subscribe(token => {
				this.userService.getFollowing(token, params['login']).subscribe(following => {
					this.following = following;
				});
			});
		});
	}

}
