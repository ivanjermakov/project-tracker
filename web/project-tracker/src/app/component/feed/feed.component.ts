import {Component, OnInit} from '@angular/core';
import {ProjectService} from '../../service/project.service';
import {TokenProviderService} from '../../service/token.provider.service';
import {Pageable} from '../../dto/Pageable';
import {PROJECTS_IN_FEED} from '../../../globals';

@Component({
	selector: 'app-feed',
	templateUrl: './feed.component.html',
	styleUrls: ['./feed.component.scss']
})
export class FeedComponent implements OnInit {

	projects = [];

	constructor(
		private projectService: ProjectService,
		private tokenProviderService: TokenProviderService
	) {
		this.tokenProviderService.token.subscribe(token => {
			projectService.all(token, new Pageable(0, PROJECTS_IN_FEED)).subscribe(ps => {
				this.projects = ps;
			});
		});
	}

	ngOnInit() {
	}

}
