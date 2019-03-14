import {Component, OnInit} from '@angular/core';

@Component({
	selector: 'app-feed',
	templateUrl: './feed.component.html',
	styleUrls: ['./feed.component.scss']
})
export class FeedComponent implements OnInit {

	projects = [{
		'id': 6,
		'isPublic': true,
		'created': '2019-03-14 10:48:03',
		'name': 'project',
		'description': null,
		'about': null,
		'progress': 0
	}, {
		'id': 6,
		'isPublic': true,
		'created': '2019-03-14 10:48:03',
		'name': 'project2',
		'description': null,
		'about': null,
		'progress': 0
	}, {
		'id': 6,
		'isPublic': false,
		'created': '2019-03-14 10:48:03',
		'name': 'project',
		'description': null,
		'about': null,
		'progress': 0
	}, {
		'id': 6,
		'isPublic': true,
		'created': '2019-03-14 10:48:03',
		'name': 'project3',
		'description': null,
		'about': null,
		'progress': 0
	}];

	constructor() {
	}

	ngOnInit() {
	}

}
