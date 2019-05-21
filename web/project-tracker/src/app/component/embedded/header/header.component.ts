import {Component, OnInit} from '@angular/core';
import {LOGO_BW_SRC, LOGO_SRC} from '../../../../globals';
import {AppComponent} from '../../../app.component';
import {TokenProvider} from '../../../provider/token.provider';
import {AuthService} from '../../../service/auth.service';
import {User} from '../../../dto/User';

@Component({
	selector: 'app-header',
	templateUrl: './header.component.html',
	styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {

	LOGO_SRC = LOGO_SRC;
	LOGO_BW_SRC = LOGO_BW_SRC;

	me: User;

	constructor(
		private app: AppComponent,
		private tokenProvider: TokenProvider,
		private authService: AuthService
	) {
	}

	ngOnInit() {
		this.app.onLoad(() => {
			this.tokenProvider.token.subscribe(token => {
				this.authService.validate(token).subscribe(me => {
					this.me = me;
				});
			});
		});
	}

}
