import {Injectable} from "@angular/core";
import Dexie from "dexie";
import { v4 as uuidv4 } from 'uuid'

import {Todo, TodoSummary} from "../models";

@Injectable()
export class TodoService extends Dexie {

	todo: Dexie.Table<Todo, string>

	constructor() {
		super('todo-db')
		this.version(1).stores({
			todo: 'tid'
		})

		this.todo = this.table('todo')
	}

	getTodoSummary(): Promise<TodoSummary[]> {
		return this.todo.toArray()
			.then(todos => 
				todos.map(t => ({ tid: t.tid, title: t.title } as TodoSummary)))
	}

	updateTodo(todo: Todo): Promise<string> {
		return this.todo.put(todo)
	}

	deleteTodoById(tid: string): Promise<void> {
		return this.todo.delete(tid)
	}

	addTodo(todo: Todo): Promise<string> {
		todo.tid = uuidv4().toString().substring(0, 8)
		return this.todo.add(todo)
	}

	getTodoById(tid: string): Promise<Todo> {
		return <Promise<Todo>> this.todo.get(tid) 
	}
}
