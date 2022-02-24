import { Component, OnInit } from '@angular/core';

import {TaskService} from '../task.service';
import { Task } from '../models'

@Component({
  selector: 'app-list',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.css']
})
export class ListComponent implements OnInit {

	tasks: Task[] = [] 

  constructor(private taskSvc: TaskService) { }

  ngOnInit(): void {
		this.taskSvc.getAllTasks()
			.then(result => this.tasks = result)
  }

}
