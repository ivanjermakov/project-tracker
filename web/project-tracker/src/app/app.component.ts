import {Component} from '@angular/core';
import {NavigationEnd, Router} from '@angular/router';
import {LOCALSTORAGE_TOKEN_NAME} from '../globals';
import {TokenProviderService} from './service/token.provider.service';

@Component({
	selector: 'app-root',
	templateUrl: './app.component.html',
	styleUrls: ['./app.component.scss']
})
export class AppComponent {

	constructor(
		private router: Router,
		private tokenProviderService: TokenProviderService
	) {
		this.autoLogin();
		this.getViewPath((url) => {
			if (url === '/') {
				this.router.navigate(['/auth']);
			}
		});
	}

	getViewPath(event: (url: string) => void) {
		this.router.events.subscribe(e => {
			if (e instanceof NavigationEnd) {
				if (e.url === '/') {
					event(e.url);
				}
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
