import {Component} from '@angular/core';
import {NavigationEnd, Router} from '@angular/router';

@Component({
	selector: 'app-root',
	templateUrl: './app.component.html',
	styleUrls: ['./app.component.scss']
})
export class AppComponent {

	constructor(private router: Router) {
		this.getViewPath((url) => {
			if (url === '/') {
				this.router.navigate(['/auth'], {replaceUrl: true});
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

}
