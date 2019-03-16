import {Component} from '@angular/core';
import {Router} from '@angular/router';
import {LOCALSTORAGE_TOKEN_NAME} from '../globals';
import {TokenProviderService} from './service/token.provider.service';
import {UrlService} from './service/url.service';
import {UserProviderService} from './service/user.provider.service';
import {AuthService} from './service/auth.service';

@Component({
	selector: 'app-root',
	templateUrl: './app.component.html',
	styleUrls: ['./app.component.scss']
})
export class AppComponent {

	constructor(
		private router: Router,
		private authService: AuthService,
		private tokenProviderService: TokenProviderService,
		private userProviderService: UserProviderService,
		private urlService: UrlService
	) {
		this.urlService.getViewPath((url) => {
			if (url !== '/auth' && url !== '/register') {
				this.autoLogin(() => {
					if (url === '/') {
						this.router.navigate(['/feed']);
					}
				}, () => {
					this.router.navigate(['/auth']);
				});
			}
		});
	}

	private autoLogin(success: () => void, error: () => void): void {
		this.tokenProviderService.token.subscribe(t => {
			if (t) {
				return success();
			}

			console.debug('auto login attempt');
			let token = localStorage.getItem(LOCALSTORAGE_TOKEN_NAME);
			if (!token) {
				console.debug('no token in localstorage');
				return error();
			}

			console.debug('token from localstorage:  ' + token);
			this.tokenProviderService.setToken(token);
			this.authService.validate(token).subscribe(user => {
				this.userProviderService.setMe(user);
			}, error => {
				this.tokenProviderService.setToken(null);
				error();
			});
			return success();
		});
	}

}
