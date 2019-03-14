import {Component, OnInit} from '@angular/core';
import {AuthUser} from '../../dto/AuthUser';
import {AuthService} from '../../service/auth.service';
import {Router} from '@angular/router';

@Component({
	selector: 'app-auth',
	templateUrl: './auth.component.html',
	styleUrls: [
		'./auth.component.scss',
		'./auth.component.overlay.scss'
	]
})
export class AuthComponent implements OnInit {

	authUser: AuthUser = new AuthUser();

	constructor(
		private authService: AuthService,
		private router: Router
	) {
	}

	ngOnInit() {
	}

	authenticate() {
		this.authService.authenticate(this.authUser).subscribe(token => {
			console.log('TOKEN: ' + token);
			this.router.navigate(['/feed'], {replaceUrl: true});
		});
	}
}
