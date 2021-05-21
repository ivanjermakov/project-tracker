import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {API_URL} from '../../globals';
import {Pageable} from '../dto/Pageable';
import {HttpClient} from '@angular/common/http';
import {NewTask} from '../dto/NewTask';
import {EditTask} from '../dto/EditTask';
import {Task} from '../dto/Task';
import {ListTask} from '../dto/ListTask';

@Injectable({
	providedIn: 'root'
})
export class TaskService {

	constructor(
		private http: HttpClient
	) {
	}

	create(token: string, newTask: NewTask): Observable<Task> {
		return this.http.post<Task>(API_URL + 'task/create', newTask, {headers: {token: token}});
	}

	all(token: string, projectId: number, pageable: Pageable): Observable<Task[]> {
		return this.http.get<Task[]>(API_URL + 'task/all', {
			params: pageable.toHttpParams()
				.append('projectId', projectId.toString()),
			headers: {token: token}
		});
	}

	owned(token: string, pageable: Pageable): Observable<Task[]> {
		return this.http.get<Task[]>(API_URL + 'task/owned', {
			params: pageable.toHttpParams(),
			headers: {token: token}
		});
	}

	assignee(token: string, pageable: Pageable): Observable<Task[]> {
		return this.http.get<Task[]>(API_URL + 'task/assignee', {
			params: pageable.toHttpParams(),
			headers: {token: token}
		});
	}

	get(token: string, taskId: number): Observable<Task> {
		return this.http.get<Task>(API_URL + 'task/get', {
			params: {taskId: taskId.toString()},
			headers: {token: token}
		});
	}

	edit(token: string, editTask: EditTask): Observable<Task> {
		return this.http.post<Task>(API_URL + 'task/edit', editTask, {headers: {token: token}});
	}

	delete(token: string, taskId: number): Observable<void> {
		return this.http.post<void>(API_URL + 'task/delete', taskId, {headers: {token: token}});
	}

	list(token: string, projectId: number, listTask: ListTask, pageable: Pageable): Observable<Task[]> {
		return this.http.post<Task[]>(`${API_URL}task/${projectId}/list`, listTask, {
			params: pageable.toHttpParams(),
			headers: {token: token}
		});
	}

}
