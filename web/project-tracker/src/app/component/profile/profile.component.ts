import {Component, OnInit} from '@angular/core';
import {ProfileService} from '../../service/profile.service';
import {TokenProviderService} from '../../service/token.provider.service';
import {UserProviderService} from '../../service/user.provider.service';
import {User} from '../../dto/User';
import {ActivatedRoute, Router} from '@angular/router';
import {AppComponent} from '../../app.component';
import {TimeService} from '../../service/time.service';
import {ProfileTab} from './ProfileTab';
import {UrlService} from '../../service/url.service';

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
	editMode: boolean = false;
	tab: ProfileTab;

	constructor(
		private urlService: UrlService,
		private app: AppComponent,
		private profileService: ProfileService,
		private tokenProviderService: TokenProviderService,
		private userProviderService: UserProviderService,
		private route: ActivatedRoute,
		private router: Router
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
						this.tab = this.getCurrentTab();
						console.debug('tab: ', this.tab);
					});
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

}
