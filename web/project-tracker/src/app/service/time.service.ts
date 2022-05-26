import {Injectable} from '@angular/core';
import * as moment from 'moment';

@Injectable({
	providedIn: 'root'
})
export class TimeService {

	constructor() {
	}

	static formatDate(date: Date, format: string) {
		return TimeService.moment(date).format(format);
	}

	static moment(inp?: moment.MomentInput, format?: moment.MomentFormatSpecification, language?: string, strict?: boolean): moment.Moment {
		return moment(inp, format, 'ru', strict);
	}

	static isPast(date: Date) {
		return TimeService.moment(date).isAfter(moment());
	}
}
