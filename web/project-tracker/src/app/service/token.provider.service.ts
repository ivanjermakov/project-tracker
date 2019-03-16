import {Injectable} from '@angular/core';
import {BehaviorSubject} from 'rxjs';

@Injectable({
	providedIn: 'root'
})
export class TokenProviderService {

	private tokenSubject = new BehaviorSubject<string>(null);
	token = this.tokenSubject.asObservable();

	constructor() {
	}

	setToken(token: string) {
		this.tokenSubject.next(token);
	}

}
