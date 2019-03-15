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
		this.autoLogin();
		this.urlService.getViewPath((url) => {
			if (url === '/') {
				this.router.navigate(['/auth']);
			}
		});
	}

	private autoLogin() {
		let token = localStorage.getItem(LOCALSTORAGE_TOKEN_NAME);
		if (!token) {
			return;
		}

		this.tokenProviderService.setToken(token);
		this.router.navigate(['/feed']);
	}

}
