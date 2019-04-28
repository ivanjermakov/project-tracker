import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {Project} from '../dto/Project';
import {API_URL} from '../../globals';
import {HttpClient} from '@angular/common/http';
import {User} from '../dto/User';

@Injectable({
	providedIn: 'root'
})
export class ProfileService {

	constructor(
		private http: HttpClient
	) {
	}

	get(token: string, login: string): Observable<User> {
		return this.http.get<User>(API_URL + `profile/${login}/get`, {
			headers: {token: token}
		});
	}

}
