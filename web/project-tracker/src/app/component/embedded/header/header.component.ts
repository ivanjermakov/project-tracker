import {Component, OnInit} from '@angular/core';
import {LOCALSTORAGE_TOKEN_NAME, LOGO_BW_SRC, LOGO_SRC} from '../../../../globals';
import {AppComponent} from '../../../app.component';
import {TokenProvider} from '../../../provider/token.provider';
import {AuthService} from '../../../service/auth.service';
import {User} from '../../../dto/User';
import {Router} from '@angular/router';

@Component({
	selector: 'app-header',
	templateUrl: './header.component.html',
	styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {

	LOGO_SRC = LOGO_SRC;
	LOGO_BW_SRC = LOGO_BW_SRC;

	me: User;
	displayName: string;

	constructor(
		private app: AppComponent,
		private tokenProvider: TokenProvider,
		private authService: AuthService,
		private router: Router
	) {
	}

	ngOnInit() {
		this.app.onLoad(() => {
			this.tokenProvider.token.subscribe(token => {
				this.authService.validate(token).subscribe(me => {
					this.me = me;
					this.displayName = me.userInfo.name ? me.userInfo.name : `@${me.login}`;
				});
			});
		});
	}

	logOut() {
		if (!confirm('Are you sure want to log out?')) {
			return;
		}
		this.tokenProvider.setToken(null);
		localStorage.removeItem(LOCALSTORAGE_TOKEN_NAME);
		this.router.navigate(['/login']);
	}
}
