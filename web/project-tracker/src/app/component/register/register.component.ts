import {Component, OnInit} from '@angular/core';
import {VerifiableRegisterUser} from '../../dto/VerifiableRegisterUser';
import {RegisterService} from '../../service/register.service';
import {RegisterUser} from '../../dto/RegisterUser';
import {Router} from '@angular/router';

@Component({
	selector: 'app-register',
	templateUrl: './register.component.html',
	styleUrls: [
		'./register.component.scss',
		'./../auth/auth.component.overlay.scss'
	]
})
export class RegisterComponent implements OnInit {

	private verifiableRegisterUser: VerifiableRegisterUser = new VerifiableRegisterUser();

	constructor(
		private registerService: RegisterService,
		private router: Router
	) {
	}

	ngOnInit() {
	}

	register() {
		if (this.verifiableRegisterUser.password !== this.verifiableRegisterUser.confirmPassword) {
			return;
		}
		this.registerService.register(new RegisterUser(this.verifiableRegisterUser.login, this.verifiableRegisterUser.password))
			.subscribe(success => {
				console.log('registered successfully!');
				this.router.navigate(['/auth'], {replaceUrl: true});
			});
	}
}
