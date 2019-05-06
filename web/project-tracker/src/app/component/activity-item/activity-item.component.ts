import {Component, Input, OnInit} from '@angular/core';
import {Activity} from '../../dto/Activity';
import {TimeService} from '../../service/time.service';

@Component({
	selector: 'app-activity-item',
	templateUrl: './activity-item.component.html',
	styleUrls: ['./activity-item.component.scss']
})
export class ActivityItemComponent implements OnInit {

	@Input()
	activity: Activity;

	constructor() {
	}

	ngOnInit() {
	}

	formatDateFromNow(date: Date): string {
		return TimeService.moment(date).fromNow();
	}

}
