import {Component, Input, OnInit} from '@angular/core';
import {Activity} from '../../../dto/Activity';

@Component({
	selector: 'app-activity-list',
	templateUrl: './activity-list.component.html',
	styleUrls: ['./activity-list.component.scss']
})
export class ActivityListComponent implements OnInit {

	@Input()
	activities: Activity[];

	constructor() {
	}

	ngOnInit() {
	}

}
