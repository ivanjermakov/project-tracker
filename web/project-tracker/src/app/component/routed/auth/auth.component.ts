import {Component, OnInit} from '@angular/core';
import {AuthUser} from '../../../dto/AuthUser';
import {AuthService} from '../../../service/auth.service';
import {Router} from '@angular/router';
import {LOCALSTORAGE_TOKEN_NAME} from '../../../../globals';
import {TokenProvider} from '../../../provider/token.provider';

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
		private router: Router,
		private tokenProvider: TokenProvider
	) {
	}

	ngOnInit() {
	}

	authenticate() {
		this.authService.authenticate(this.authUser).subscribe(token => {
			this.tokenProvider.setToken(token);
			localStorage.setItem(LOCALSTORAGE_TOKEN_NAME, token);

			this.router.navigate(['/feed'], {replaceUrl: true});
		});
	}
}
