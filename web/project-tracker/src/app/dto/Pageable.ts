import {HttpParams} from '@angular/common/http';
import {Direction} from './SortDirection';

export class Pageable {

	page: number;
	size: number;
	sort: string;
	direction: Direction;

	constructor(page: number, size: number, sort?: string, direction?: Direction) {
		this.page = page;
		this.size = size;
		this.sort = sort;
		this.direction = direction;
	}

	toHttpParams(): HttpParams {
		let params = new HttpParams()
			.append('page', this.page.toString())
			.append('size', this.size.toString());

		params = this.sort && this.direction !== null
			? params.append('sort', `${this.sort},${Direction[this.direction]}`)
			: params;

		return params;
	}
}
