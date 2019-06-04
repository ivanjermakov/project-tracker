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

@Component({
	selector: 'app-profile',
	templateUrl: './profile.component.html',
	styleUrls: [
		'./profile.component.scss',
		'./../../../app.component.scss',
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
		private tokenProvider: TokenProvider,
		private route: ActivatedRoute,
		private router: Router,
		private titleService: Title
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
						console.debug('profile user: ', this.user);
						this.titleService.setTitle(`@${this.user.login}`);
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

}
