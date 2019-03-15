import {HttpParams} from '@angular/common/http';

export class Pageable {
	page: number;
	size: number;

	constructor(page: number, size: number) {
		this.page = page;
		this.size = size;
	}

	toHttpParams(): HttpParams {
		return new HttpParams()
			.append('page', this.page.toString())
			.append('size', this.size.toString());
	}
}
