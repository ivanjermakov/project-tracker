import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {API_URL} from '../../globals';
import {Pageable} from '../dto/Pageable';
import {NewActivity} from '../dto/NewActivity';
import {Activity} from '../dto/Activity';

@Injectable({
	providedIn: 'root'
})
export class ActivityService {

	constructor(
		private http: HttpClient
	) {
	}

	create(token: string, newActivity: NewActivity): Observable<Activity> {
		return this.http.post<Activity>(API_URL + 'activity/create', newActivity, {headers: {token: token}});
	}

	allByTask(token: string, taskId: number, pageable: Pageable): Observable<Activity[]> {
		return this.http.get<Activity[]>(API_URL + 'activity/allByTask', {
			params: pageable.toHttpParams()
				.append('taskId', taskId.toString()),
			headers: {token: token}
		});
	}

	getLastByTask(token: string, taskId: number): Observable<Activity> {
		return this.http.get<Activity>(API_URL + 'activity/getLastByTask', {
			params: {taskId: taskId.toString()},
			headers: {token: token}
		});
	}

	get(token: string, activityId: number): Observable<Activity> {
		return this.http.get<Activity>(API_URL + 'activity/get', {
			params: {taskId: activityId.toString()},
			headers: {token: token}
		});
	}

	delete(token: string, activityId: number): Observable<void> {
		return this.http.post<void>(API_URL + 'activity/delete', activityId, {headers: {token: token}});
	}

}
