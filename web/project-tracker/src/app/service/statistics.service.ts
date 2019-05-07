import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {ProjectActivity} from '../dto/stats/ProjectActivity';
import {API_URL} from '../../globals';

@Injectable({
	providedIn: 'root'
})
export class StatisticsService {

	constructor(
		private http: HttpClient
	) {
	}

	getProjectActivities(token: string, projectId: number): Observable<ProjectActivity[]> {
		return this.http.get<ProjectActivity[]>(API_URL + 'stats/project-activity', {
			params: {projectId: projectId.toString()},
			headers: {token: token}
		});
	}

}
