import {Injectable} from '@angular/core';
import {NavigationEnd, Router} from '@angular/router';

@Injectable({
	providedIn: 'root'
})
export class UrlService {

	constructor(
		private router: Router
	) {
	}

	getViewPath(event: (url: string) => void) {
		this.router.events.subscribe(e => {
			if (e instanceof NavigationEnd) {
				event(e.url);
			}
		});
	}

}
