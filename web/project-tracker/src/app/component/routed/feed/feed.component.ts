import {Component, OnInit} from '@angular/core';
import {ProjectService} from '../../../service/project.service';
import {TokenProvider} from '../../../provider/token.provider';
import {Pageable} from '../../../dto/Pageable';
import {ACTIVITIES_IN_FEED, PROJECTS_IN_FEED} from '../../../../globals';
import {UserProvider} from '../../../provider/user.provider';
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
		private tokenProvider: TokenProvider,
		private userProvider: UserProvider,
	) {
	}

	ngOnInit() {
		this.app.onLoad(() => {
			console.debug('feed initiation');
			this.userProvider.me.subscribe(me => {
				this.me = me;
			});
			this.tokenProvider.token.subscribe(token => {
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
