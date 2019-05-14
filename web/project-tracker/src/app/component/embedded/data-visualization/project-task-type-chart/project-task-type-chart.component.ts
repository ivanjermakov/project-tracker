import {Component, Input, OnInit} from '@angular/core';
import {AppComponent} from '../../../../app.component';
import {StatisticsService} from '../../../../service/statistics.service';
import {ChartConfig} from '../../../../dto/shared/ChartConfig';
import {Project} from '../../../../dto/Project';
import {TokenProvider} from '../../../../provider/token.provider';

@Component({
	selector: 'app-project-task-type-chart',
	templateUrl: './project-task-type-chart.component.html',
	styleUrls: ['./project-task-type-chart.component.scss']
})
export class ProjectTaskTypeChartComponent implements OnInit {

	@Input()
	project: Project;
	chartConfig: ChartConfig;

	constructor(
		private app: AppComponent,
		private tokenProvider: TokenProvider,
		private statisticsService: StatisticsService,
	) {
	}

	ngOnInit() {
		this.app.onLoad(() => {
			this.tokenProvider.token.subscribe(token => {
				this.statisticsService.getProjectTaskTypes(token, this.project.id).subscribe(projectTaskTypes => {
					this.chartConfig = new ChartConfig(
						[
							{
								data: projectTaskTypes.map(a => a.count),
								label: 'Task types',
								backgroundColor: ['#89D73A', '#CED73E', '#D75A63', '#5390E4']
							}
						],
						projectTaskTypes.map(a => a.type.toString()[0] + a.type.toString().slice(1).toLowerCase()),
						{
							tooltips: {
								displayColors: false
							},
							legend: {
								position: 'left'
							},
							responsive: true,
							aspectRatio: 5,
						},
						true,
						'doughnut'
					);
					console.debug(this.chartConfig);
				});
			});
		});
	}

}
