import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {User} from '../../../dto/User';

@Component({
	selector: 'app-profile-preview',
	templateUrl: './profile-preview.component.html',
	styleUrls: ['./profile-preview.component.scss']
})
export class ProfilePreviewComponent implements OnInit {

	@Input()
	user: User;

	constructor() {
	}

	ngOnInit(): void {
	}

}
