import {Injectable} from '@angular/core';

@Injectable({
	providedIn: 'root'
})
export class ArrayService {

	constructor() {
	}

	filter<T>(array: T[], predicate: (item: T) => boolean, callback: (array: T[]) => void): void {
		const resultArray = [];

		array.forEach(i => {
			if (predicate(i)) {
				resultArray.push(i);
			}
		});

		callback(resultArray);
	}

}
