import {HttpParams} from '@angular/common/http';

export class Pageable {
	page: number;
	size: number;

	constructor(page: number, size: number) {
		this.page = page;
		this.size = size;
	}

	toHttpParams() {
		const httpParams = new HttpParams();
		httpParams.append('page', this.page.toString());
		httpParams.append('size', this.size.toString());

		return httpParams;
	}
}
