import {Injectable} from '@angular/core';
import {BehaviorSubject} from 'rxjs';

@Injectable({
	providedIn: 'root'
})
export class TokenProvider {

	private tokenSubject = new BehaviorSubject<string>(null);
	token = this.tokenSubject.asObservable();

	setToken(token: string) {
		this.tokenSubject.next(token);
	}

}
