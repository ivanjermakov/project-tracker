import {Component, OnInit} from '@angular/core';
import {ProjectService} from '../../service/project.service';
import {TokenProviderService} from '../../service/token.provider.service';
import {Pageable} from '../../dto/Pageable';
import {PROJECTS_IN_FEED} from '../../../globals';
import {UserProviderService} from '../../service/user.provider.service';
import {User} from '../../dto/User';
import {AppComponent} from '../../app.component';

@Component({
	selector: 'app-feed',
	templateUrl: './feed.component.html',
	styleUrls: [
		'./feed.component.scss',
		'./../../app.component.scss'
	]
})
export class FeedComponent implements OnInit {

	projects = [];
	me: User;

	constructor(
		private app: AppComponent,
		private projectService: ProjectService,
		private tokenProviderService: TokenProviderService,
		private userProviderService: UserProviderService,
	) {
	}

	ngOnInit() {
		this.app.onLoad(() => {
			console.debug('feed initiation');
			this.userProviderService.me.subscribe(me => {
				this.me = me;
			});
			this.tokenProviderService.token.subscribe(token => {
				this.projectService.all(token, new Pageable(0, PROJECTS_IN_FEED)).subscribe(projects => {
					this.projects = projects;
				});
			});
		});
	}

}
