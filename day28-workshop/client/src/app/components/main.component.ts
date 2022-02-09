import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import {TodoSummary} from '../models';
import {TodoService} from '../services/todo.service';

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css']
})
export class MainComponent implements OnInit {

	summary: TodoSummary[] = []

	constructor(private todoSvc: TodoService, private router: Router) { }

	ngOnInit(): void {
		this.todoSvc.getTodoSummary()
			.then(t => this.summary = t);
	}

}
