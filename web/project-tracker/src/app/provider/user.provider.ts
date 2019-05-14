import {Injectable} from '@angular/core';
import {BehaviorSubject} from 'rxjs';
import {User} from '../dto/User';

@Injectable({
	providedIn: 'root'
})
export class UserProvider {

	private meSubject = new BehaviorSubject<User>(null);
	me = this.meSubject.asObservable();

	setMe(me: User) {
		this.meSubject.next(me);
	}

}
