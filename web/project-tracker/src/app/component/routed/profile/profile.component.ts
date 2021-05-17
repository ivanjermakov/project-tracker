import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {ProfileTab} from './ProfileTab';
import {Title} from '@angular/platform-browser';
import {User} from '../../../dto/User';
import {UrlService} from '../../../service/url.service';
import {AppComponent} from '../../../app.component';
import {ProfileService} from '../../../service/profile.service';
import {TokenProvider} from '../../../provider/token.provider';
import {TimeService} from '../../../service/time.service';
import {UserService} from '../../../service/user.service';
import {AuthService} from '../../../service/auth.service';
import {EditUser} from '../../../dto/EditUser';
import {ErrorService} from '../../../service/error.service';

@Component({
	selector: 'app-profile',
	templateUrl: './profile.component.html',
	styleUrls: [
		'./profile.component.scss',
		'./../../../app.component.scss',
		'./../../../style/edit.scss',
	]
})
export class ProfileComponent implements OnInit {

	user: User;
	beforeEditUser: User;
	me: User;
	editMode: boolean = false;
	tab: ProfileTab;
	followed: boolean;

	constructor(
		private urlService: UrlService,
		private app: AppComponent,
		private profileService: ProfileService,
		private tokenProvider: TokenProvider,
		private route: ActivatedRoute,
		private router: Router,
		private titleService: Title,
		private userService: UserService,
		private authService: AuthService,
		private errorService: ErrorService
	) {
	}

	ngOnInit() {
		this.app.onLoad(() => {
			console.debug('profile initiation');
			this.route.params.subscribe(params => {
				console.debug('params', params);
				this.tokenProvider.token.subscribe(token => {
					this.profileService.get(token, params['login']).subscribe(user => {
						this.user = user;
						this.authService.validate(token).subscribe(me => {
							this.me = me;
							console.debug('profile user: ', this.user);
							this.titleService.setTitle(`@${this.user.login}`);

							this.userService.getFollowing(token, this.me.login).subscribe(following => {
								this.followed = following.filter(u => u.id === user.id).length > 0;
							});
						});
					});
					this.tab = this.getCurrentTab();
					console.debug('tab: ', this.tab);
				});
			});
		});
	}

	formatDate(date: Date) {
		return TimeService.formatDate(date, 'MMMM Do[, ] YYYY');
	}

	changeTab(tab: string) {
		this.tab = ProfileTab[tab.toUpperCase()];
		this.router.navigate(['/' + this.user.login + '/' + tab.toLowerCase()]);
		console.debug('current tab: ', this.tab);
	}

	getCurrentTab(): ProfileTab {
		const tab = ProfileTab[this.router.url.split('/').slice(-1)[0].toUpperCase()];
		return tab ? tab : ProfileTab.OVERVIEW;
	}

	follow() {
		this.tokenProvider.token.subscribe(token => {
			this.userService.follow(token, this.user.login, !this.followed).subscribe(() => {
				this.followed = !this.followed;
			});
		});
	}

	edit() {
		this.beforeEditUser = JSON.parse(JSON.stringify(this.user));
		this.editMode = true;
	}

	cancelEdit() {
		this.editMode = false;
		this.user = this.beforeEditUser;
		this.beforeEditUser = null;
	}

	applyEdit() {
		let editUser = new EditUser();
		editUser.id = this.user.id;
		editUser.login = this.user.login;
		editUser.name = this.user.userInfo.name;

		this.tokenProvider.token.subscribe(token => {
			this.profileService.edit(token, editUser).subscribe(user => {
				this.editMode = false;
				this.router.navigate([user.login]);
			}, e => this.errorService.raise(e));
		});
	}
}
