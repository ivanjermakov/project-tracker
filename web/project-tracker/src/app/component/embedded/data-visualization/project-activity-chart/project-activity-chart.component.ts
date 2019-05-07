import {Component, Input, OnInit} from '@angular/core';
import {Project} from '../../../../dto/Project';
import {AppComponent} from '../../../../app.component';
import {TokenProviderService} from '../../../../service/token.provider.service';
import {StatisticsService} from '../../../../service/statistics.service';
import {ChartConfig} from '../../../../dto/shared/ChartConfig';
import {TimeService} from '../../../../service/time.service';

@Component({
	selector: 'app-project-activity-chart',
	templateUrl: './project-activity-chart.component.html',
	styleUrls: ['./project-activity-chart.component.scss']
})
export class ProjectActivityChartComponent implements OnInit {

	@Input()
	project: Project;
	chartConfig: ChartConfig;

	constructor(
		private app: AppComponent,
		private tokenProviderService: TokenProviderService,
		private statisticsService: StatisticsService,
	) {
	}

	ngOnInit() {
		this.app.onLoad(() => {
			this.tokenProviderService.token.subscribe(token => {
				this.statisticsService.getProjectActivities(token, this.project.id).subscribe(projectActivities => {
					this.chartConfig = new ChartConfig(
						[
							{
								data: projectActivities.map(a => a.activityAmount),
								label: 'Activities',
								backgroundColor: 'rgba(83, 144, 228, 0.4)',
								borderColor: 'rgb(83, 144, 228)',
								pointBackgroundColor: 'rgb(83, 144, 228)',
								pointBorderColor: 'rgb(83, 144, 228)'
							}
						],
						projectActivities.map(a => a.day).map(d => TimeService.formatDate(d, 'MMMM Do')),
						{
							scales: {
								yAxes: [{
									ticks: {
										beginAtZero: true
									}
								}]
							},
							tooltips: {
								displayColors: false
							},
							responsive: true,
							aspectRatio: 5
						},
						true,
						'line'
					);
					console.log(this.chartConfig);
				});
			});
		});
	}

}
