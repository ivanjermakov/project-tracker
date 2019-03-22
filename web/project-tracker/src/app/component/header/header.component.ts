import {Component, OnInit} from '@angular/core';
import {LOGO_BW_SRC, LOGO_SRC} from '../../../globals';

@Component({
	selector: 'app-header',
	templateUrl: './header.component.html',
	styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {

	LOGO_SRC = LOGO_SRC;
	LOGO_BW_SRC = LOGO_BW_SRC;

	constructor() {
	}

	ngOnInit() {
	}

}
