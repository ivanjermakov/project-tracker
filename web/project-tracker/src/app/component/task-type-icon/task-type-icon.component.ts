import {Component, Input, OnInit} from '@angular/core';
import {Task} from '../../dto/Task';

@Component({
  selector: 'app-task-type-icon',
  templateUrl: './task-type-icon.component.html',
  styleUrls: ['./task-type-icon.component.scss']
})
export class TaskTypeIconComponent implements OnInit {

  @Input()
  task: Task;

  constructor() { }

  ngOnInit() {
  }

}
