import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {NewProject} from '../dto/NewProject';
import {Observable} from 'rxjs';
import {Project} from '../dto/Project';
import {API_URL} from '../../globals';
import {Pageable} from '../dto/Pageable';
import {EditProject} from '../dto/EditProject';

@Injectable({
	providedIn: 'root'
})
export class ProjectService {

	constructor(private http: HttpClient) {
	}

	create(token: string, newProject: NewProject): Observable<Project> {
		return this.http.post<Project>(API_URL + 'project/create', newProject, {headers: {token: token}});
	}

	all(token: string, pageable: Pageable): Observable<Array<Project>> {
		return this.http.get<Array<Project>>(API_URL + 'project/all', {
			params: pageable.toHttpParams(),
			headers: {token: token}
		});
	}

	get(token: string, login: string, name: string): Observable<Project> {
		return this.http.get<Project>(API_URL + `project/${login}/${name}/get`, {
			headers: {token: token}
		});
	}

	edit(token: string, editProject: EditProject) {
		return this.http.post<Project>(API_URL + 'project/edit', editProject, {headers: {token: token}});
	}
}
