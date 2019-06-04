import {Component, OnInit} from '@angular/core';
import {ProjectService} from '../../../service/project.service';
import {TokenProvider} from '../../../provider/token.provider';
import {Pageable} from '../../../dto/Pageable';
import {ACTIVITIES_IN_FEED, PROJECTS_IN_FEED} from '../../../../globals';
import {User} from '../../../dto/User';
import {AppComponent} from '../../../app.component';
import {ActivityService} from '../../../service/activity.service';
import {Subject} from 'rxjs';
import {debounceTime, distinctUntilChanged} from 'rxjs/operators';
import {Router} from '@angular/router';
import {AuthService} from '../../../service/auth.service';

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

	searchQuery: string;
	searchQueryChanged: Subject<string> = new Subject();

	constructor(
		private app: AppComponent,
		private projectService: ProjectService,
		private activityService: ActivityService,
		private authService: AuthService,
		private tokenProvider: TokenProvider,
		private router: Router
	) {
	}

	ngOnInit() {
		this.app.onLoad(() => {
			console.debug('feed initiation');

			this.tokenProvider.token.subscribe(token => {
				this.authService.validate(token).subscribe(me => {
					this.me = me;
				});
				this.projectService.all(token, new Pageable(0, PROJECTS_IN_FEED)).subscribe(projects => {
					this.projects = projects;
				});
				this.activityService.allByUser(token, new Pageable(0, ACTIVITIES_IN_FEED)).subscribe(activities => {
					this.activities = activities;
				});
				this.searchQueryChanged
					.pipe(debounceTime(100), distinctUntilChanged())
					.subscribe(searchQuery => {
						this.searchQuery = searchQuery;
						this.projectService.find(token, searchQuery).subscribe(projects => {
							this.projects = projects;
						});
					});
			});
		});
	}

	changed(searchQuery: string): void {
		this.searchQueryChanged.next(searchQuery);
	}

	openFound() {
		if (this.projects.length === 1) {
			this.tokenProvider.token.subscribe(token => {
				this.router.navigate([this.me.login, this.projects[0].name]);
			});
		}
	}
}
