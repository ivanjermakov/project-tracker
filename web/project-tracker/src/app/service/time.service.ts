import {Injectable} from '@angular/core';
import * as moment from 'moment';

@Injectable({
	providedIn: 'root'
})
export class TimeService {

	constructor() {
	}

	static formatDate(date: Date, format: string) {
		return moment(date).format(format);
	}

}
