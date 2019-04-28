import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {API_URL} from '../../globals';
import {HttpClient} from '@angular/common/http';
import {User} from '../dto/User';
import {EditUser} from '../dto/EditUser';

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

	edit(token: string, editUser: EditUser): Observable<User> {
		return this.http.post<User>(API_URL + 'profile/edit', editUser, {
			headers: {token: token}
		});
	}

}
