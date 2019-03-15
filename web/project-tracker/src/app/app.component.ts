import {Component} from '@angular/core';
import {Router} from '@angular/router';
import {LOCALSTORAGE_TOKEN_NAME} from '../globals';
import {TokenProviderService} from './service/token.provider.service';
import {UrlService} from './service/url.service';

@Component({
	selector: 'app-root',
	templateUrl: './app.component.html',
	styleUrls: ['./app.component.scss']
})
export class AppComponent {

	constructor(
		private router: Router,
		private tokenProviderService: TokenProviderService,
		private urlService: UrlService
	) {
		this.urlService.getViewPath((url) => {
			let autoLogged = this.autoLogin(url);

			if (url === '/') {
				if (autoLogged) {
					this.router.navigate(['/feed']);
				} else {
					this.router.navigate(['/auth']);
				}
			}
		});
	}

	private autoLogin(url: string): boolean {
		let token = localStorage.getItem(LOCALSTORAGE_TOKEN_NAME);
		if (!token) {
			return false;
		}

		this.tokenProviderService.setToken(token);
		return true;
	}

}
