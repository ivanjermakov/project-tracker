import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {API_URL} from '../../globals';
import {Role} from '../dto/Role';
import {SetRole} from '../dto/SetRole';


@Injectable({
	providedIn: 'root'
})
export class RoleService {

	constructor(
		private http: HttpClient
	) {
	}

	getProjectRoles(token: string, projectId: number): Observable<Role[]> {
		return this.http.get<Role[]>(`${API_URL}role/${projectId}`, {
			headers: {token: token}
		});
	}

	removeUserRole(token: string, login: string, projectId: number): Observable<void> {
		return this.http.delete<void>(`${API_URL}role/${projectId}/${login}`, {
			headers: {token: token}
		});
	}

	setUserRole(token: string, setRole: SetRole): Observable<Role> {
		return this.http.post<Role>(`${API_URL}role`, setRole, {
			headers: {token: token}
		});
	}

}
