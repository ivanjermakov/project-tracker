import {Component, Input, OnInit} from '@angular/core';
import {Project} from '../../dto/Project';

@Component({
	selector: 'app-feed-project',
	templateUrl: './feed-project.component.html',
	styleUrls: ['./feed-project.component.scss']
})
export class FeedProjectComponent implements OnInit {

	@Input('project')
	project: Project;

	constructor() {
	}

	ngOnInit() {
	}

}
