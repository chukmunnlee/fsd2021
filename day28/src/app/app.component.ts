import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ToDo } from './models';
import { ToDoService } from './todo.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {

  form!: FormGroup
  titles: string[] = []

  constructor(private todoSvc: ToDoService, private fb: FormBuilder) { }

  ngOnInit(): void {
    this.form = this.fb.group({
      title: this.fb.control(''),
      description: this.fb.control(''),
      priority: this.fb.control('low'),
    })
  }

  add() {
    const todo: ToDo = this.form.value as ToDo
    console.info('todo: ', todo)
    this.todoSvc.add(todo)
      .then(id => {
        console.info('>>> id = ', id)
        this.form.reset()
      })
      .then(result => {
        console.info(">>> result: ", result)
      })
  }
  async getAllTitles() {
    this.titles = await this.todoSvc.getAllTitles()

  }
}
