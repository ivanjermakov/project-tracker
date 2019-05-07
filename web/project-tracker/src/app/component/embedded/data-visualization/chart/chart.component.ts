import {Component, Input, OnInit} from '@angular/core';
import {ChartConfig} from '../../../../dto/shared/ChartConfig';

@Component({
	selector: 'app-chart',
	templateUrl: './chart.component.html',
	styleUrls: ['./chart.component.scss']
})
export class ChartComponent implements OnInit {

	@Input()
	chartConfig: ChartConfig;

	constructor() {
	}

	ngOnInit() {
	}

}
