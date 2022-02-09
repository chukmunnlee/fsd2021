import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import {Router} from '@angular/router';
import {TodoService} from '../todo.service';
import {TodoComponent} from './todo.component';

@Component({
  selector: 'app-add',
  templateUrl: './add.component.html',
  styleUrls: ['./add.component.css']
})
export class AddComponent implements OnInit, AfterViewInit {

	@ViewChild(TodoComponent)
	todoComponent!: TodoComponent

	valid = false

	constructor(private todoSvc: TodoService, private router: Router) { }

	ngOnInit(): void { }

	addTodo() {
		const todo = this.todoComponent.getValue()
		this.todoSvc.addTodo(todo)
			.then(() => this.router.navigate(['/']))
	}

	ngAfterViewInit() {
	}

	formValidity(v: boolean) {
		this.valid = v;
	}
}
