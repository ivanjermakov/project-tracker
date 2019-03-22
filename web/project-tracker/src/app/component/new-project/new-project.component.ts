import {Component, OnInit} from '@angular/core';
import {NewProject} from '../../dto/NewProject';

@Component({
	selector: 'app-new-project',
	templateUrl: './new-project.component.html',
	styleUrls: [
		'./new-project.component.scss',
		'./../../app.component.scss'
	]
})
export class NewProjectComponent implements OnInit {

	project: NewProject;

	constructor() {
		this.project = new NewProject();
		this.project.isPublic = true;
	}

	ngOnInit() {
	}

}
