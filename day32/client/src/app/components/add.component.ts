import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {Router} from '@angular/router';
import {TaskService} from '../task.service';

import { Task } from '../models'

@Component({
  selector: 'app-add',
  templateUrl: './add.component.html',
  styleUrls: ['./add.component.css']
})
export class AddComponent implements OnInit {

	form!: FormGroup

	constructor(private fb: FormBuilder, private taskSvc: TaskService
			, private router: Router) { }

	ngOnInit(): void {
		const now = (new Date()).toISOString().split('T')[0]
	  this.form = this.fb.group({
			username: this.fb.control('', [ Validators.required ]),
			taskName: this.fb.control('', [ Validators.required ]),
			priority: this.fb.control('low'),
			dueDate: this.fb.control(now)
	  })
	}

	addTask() {
		const task = this.form.value as Task
		this.taskSvc.postTask(task)
			.then(() => this.router.navigate(['/']))
			.catch(err => alert(err))

	}

}
