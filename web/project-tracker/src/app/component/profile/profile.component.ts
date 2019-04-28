import {Component, OnInit} from '@angular/core';
import {ProfileService} from '../../service/profile.service';
import {TokenProviderService} from '../../service/token.provider.service';
import {UserProviderService} from '../../service/user.provider.service';
import {User} from '../../dto/User';
import {ActivatedRoute} from '@angular/router';
import {AppComponent} from '../../app.component';
import {TimeService} from '../../service/time.service';

@Component({
	selector: 'app-profile',
	templateUrl: './profile.component.html',
	styleUrls: [
		'./profile.component.scss',
		'./../../app.component.scss',
	]
})
export class ProfileComponent implements OnInit {

	user: User;
	editMode:boolean = false;

	constructor(
		private app: AppComponent,
		private profileService: ProfileService,
		private tokenProviderService: TokenProviderService,
		private userProviderService: UserProviderService,
		private route: ActivatedRoute
	) {
	}

	ngOnInit() {
		this.app.onLoad(() => {
			console.debug('profile initiation');
			this.route.params.subscribe(params => {
				console.debug('params', params);
				this.tokenProviderService.token.subscribe(token => {
					this.profileService.get(token, params['login']).subscribe(user => {
						this.user = user;
						console.debug('profile user: ', this.user);
					});
				});
			});
		});
	}

	formatDate(date: Date) {
		return TimeService.formatDate(date, 'MMMM Do[, ] YYYY');
	}

}
