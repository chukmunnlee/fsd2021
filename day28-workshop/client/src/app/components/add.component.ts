import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import {Router} from '@angular/router';
import {TodoGuard} from '../models';
import {TodoService} from '../services/todo.service';
import {TodoComponent} from './todo.component';

@Component({
  selector: 'app-add',
  templateUrl: './add.component.html',
  styleUrls: ['./add.component.css']
})
export class AddComponent implements OnInit, TodoGuard {

	@ViewChild(TodoComponent)
	todoComponent!: TodoComponent

	valid = false

	constructor(private todoSvc: TodoService, private router: Router) { }

	ngOnInit(): void { }

	addTodo() {
		const todo = this.todoComponent.getValue()
		this.todoSvc.addTodo(todo)
			.then(() => {
				this.todoComponent.resetForm()
				this.router.navigate(['/'])
			})
	}

	evaluate() {
		return this.todoComponent.evaluate()
	}

	formValidity(v: boolean) {
		this.valid = v;
	}
}
