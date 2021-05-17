import {Injectable} from '@angular/core';
import {HttpErrorResponse} from '@angular/common/http';

@Injectable({
	providedIn: 'root'
})
export class ErrorService {

	constructor() {
	}

	raise(httpError: HttpErrorResponse) {
		alert(`error: ${httpError.error.message}`);
		console.log(`error: ${httpError.error.message}`);
	}

}
