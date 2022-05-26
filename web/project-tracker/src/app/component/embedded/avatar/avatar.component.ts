import {Component, Input, OnInit} from '@angular/core';
import {User} from '../../../dto/User';

@Component({
	selector: 'app-avatar',
	templateUrl: './avatar.component.html',
	styleUrls: ['./avatar.component.scss']
})
export class AvatarComponent implements OnInit {

	@Input()
	url: string;

	@Input()
	user: User;

	constructor() {
	}

	ngOnInit(): void {
	}

}
