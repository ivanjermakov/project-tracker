import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {API_URL} from '../../globals';
import {User} from '../dto/User';

@Injectable({
	providedIn: 'root'
})
export class UserService {

	constructor(
		private http: HttpClient
	) {
	}

	follow(token: string, login: string, follow: boolean): Observable<void> {
		return this.http.get<void>(`${API_URL}user/${login}/follow`, {
			params: {
				follow: follow.toString()
			},
			headers: {token: token}
		});
	}

	getFollowers(token: string, login: string): Observable<User[]> {
		return this.http.get<User[]>(`${API_URL}user/${login}/followers`, {
			headers: {token: token}
		});
	}

	getFollowing(token: string, login: string): Observable<User[]> {
		return this.http.get<User[]>(`${API_URL}user/${login}/following`, {
			headers: {token: token}
		});
	}

	find(token: string, query: string): Observable<User[]> {
		return this.http.get<User[]>(`${API_URL}user/find`, {
			headers: {token: token},
			params: {query: query}
		});
	}

}
