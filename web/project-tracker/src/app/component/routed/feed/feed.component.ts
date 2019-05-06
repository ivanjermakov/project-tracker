import {Component, OnInit} from '@angular/core';
import {ProjectService} from '../../../service/project.service';
import {TokenProviderService} from '../../../service/token.provider.service';
import {Pageable} from '../../../dto/Pageable';
import {ACTIVITIES_IN_FEED, PROJECTS_IN_FEED} from '../../../../globals';
import {UserProviderService} from '../../../service/user.provider.service';
import {User} from '../../../dto/User';
import {AppComponent} from '../../../app.component';
import {ActivityService} from '../../../service/activity.service';

@Component({
	selector: 'app-feed',
	templateUrl: './feed.component.html',
	styleUrls: [
		'./feed.component.scss',
		'./../../../app.component.scss'
	]
})
export class FeedComponent implements OnInit {

	projects = [];
	activities = [];
	me: User;

	constructor(
		private app: AppComponent,
		private projectService: ProjectService,
		private activityService: ActivityService,
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
				this.activityService.allByUser(token, new Pageable(0, ACTIVITIES_IN_FEED)).subscribe(activities => {
					this.activities = activities;
				});
			});
		});
	}

}
