import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {API_URL} from '../../globals';
import {RegisterUser} from '../dto/RegisterUser';

@Injectable({
	providedIn: 'root'
})
export class RegisterService {

	constructor(
		private http: HttpClient
	) {
	}

	register(registerUser: RegisterUser): Observable<void> {
		return this.http.post<void>(API_URL + 'register', registerUser);
	}

}
