import {Injectable} from '@angular/core';
import {HttpErrorResponse} from '@angular/common/http';

@Injectable({
	providedIn: 'root'
})
export class ErrorService {

	constructor() {
	}

	raise(httpError: HttpErrorResponse) {
		httpError.error.errors.forEach(e => {
			alert(`error: ${e.defaultMessage}`);
			console.log(`error: ${e.defaultMessage}`);
		});
	}

}
