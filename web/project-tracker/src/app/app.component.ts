import {Component} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {LOCALSTORAGE_TOKEN_NAME} from '../globals';
import {UrlService} from './service/url.service';
import {AuthService} from './service/auth.service';
import {Title} from '@angular/platform-browser';
import {TokenProvider} from './provider/token.provider';
import {HttpErrorResponse} from '@angular/common/http';

@Component({
	selector: 'app-root',
	templateUrl: './app.component.html',
	styleUrls: ['./app.component.scss']
})
export class AppComponent {

	isLoaded: boolean = false;

	constructor(
		private router: Router,
		private activatedRoute: ActivatedRoute,
		private titleService: Title,
		private authService: AuthService,
		private tokenProvider: TokenProvider,
		private urlService: UrlService
	) {
		console.debug('app initiation');

		this.urlService.getViewPath((url) => {
			if (url !== '/') {
				this.activatedRoute.firstChild.data.subscribe(d => {
					console.debug('view: ' + d['title']);
					this.titleService.setTitle(d['title']);
				});
			}

			if (url !== '/auth' && url !== '/register') {
				this.autoLogin(() => {
					if (url === '/') {
						this.router.navigate(['/feed']);
					}
				}, (e) => {
					if (e && !e.ok) {
						this.router.navigate(['error/500']);
					} else {
						this.router.navigate(['/auth']);
					}
				});
			}
		});
	}

	onLoad(loaded: () => void) {
		setTimeout(() => {
			if (this.isLoaded) {
				console.debug('onload: loaded...');
				loaded();
			} else {
				this.onLoad(loaded);
				console.debug('onload: waiting...');
			}
		}, 10);
	}

	private autoLogin(success: () => void, error: (e: HttpErrorResponse) => void): void {
		this.tokenProvider.token.subscribe(t => {
			if (t) {
				this.isLoaded = true;
				return success();
			}

			console.debug('app: auto login attempt');
			let token = localStorage.getItem(LOCALSTORAGE_TOKEN_NAME);
			if (!token) {
				console.debug('app: no token in localstorage');
				return error(null);
			}

			console.debug('app: token from localstorage:  ' + token);
			this.authService.validate(token).subscribe(user => {
				console.debug('app: received \'me\'', user);
				this.tokenProvider.setToken(token);
				this.isLoaded = true;
				return success();
			}, e => {
				console.debug('app: error validating token');
				this.tokenProvider.setToken(null);
				return error(e);
			});
		});
	}

}
