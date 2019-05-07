import {Label} from 'ng2-charts';
import {ChartDataSets, ChartOptions, ChartType} from 'chart.js';

export class ChartConfig {
	datasets: ChartDataSets[];
	labels: Label[];
	options: ChartOptions;
	legend: boolean;
	chartType: ChartType;

	constructor(datasets: ChartDataSets[], labels: Label[], options: ChartOptions, legend: boolean, chartType: 'line' | 'bar' | 'horizontalBar' | 'radar' | 'doughnut' | 'polarArea' | 'bubble' | 'pie' | 'scatter') {
		this.datasets = datasets;
		this.labels = labels;
		this.options = options;
		this.legend = legend;
		this.chartType = chartType;
	}
}
