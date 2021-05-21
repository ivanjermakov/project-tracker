import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {API_URL} from '../../globals';
import {Comment} from '../dto/Comment';
import {NewComment} from '../dto/NewComment';

@Injectable({
	providedIn: 'root'
})
export class CommentService {

	constructor(
		private http: HttpClient
	) {
	}

	getAll(token: string, taskId: number): Observable<Comment[]> {
		return this.http.get<Comment[]>(API_URL + 'comment', {
			params: {
				taskId: taskId.toString()
			},
			headers: {token: token}
		});
	}

	post(token: string, newComment: NewComment): Observable<Comment> {
		return this.http.post<Comment>(API_URL + 'comment', newComment, {
			headers: {token: token}
		});
	}

}
