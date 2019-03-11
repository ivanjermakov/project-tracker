import {Injectable} from '@angular/core';
import {AuthUser} from '../dto/AuthUser';
import {Observable} from 'rxjs';
import {HttpClient} from '@angular/common/http';
import {API_URL} from '../../globals';

@Injectable({
	providedIn: 'root'
})
export class AuthService {

	constructor(private http: HttpClient) {
	}

	authenticate(authUser: AuthUser): Observable<string> {
		return this.http.post<string>(API_URL + 'auth', authUser, {responseType: 'text' as 'json'});
	}

}
