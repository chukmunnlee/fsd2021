import { Injectable } from "@angular/core";
import Dexie from "dexie";
import { v4 as uuidv4 } from 'uuid'
import { ToDo } from "./models";

@Injectable()
export class ToDoService extends Dexie {

  todo!: Dexie.Table<ToDo, string>

  constructor() {
    super('todo-db')

    this.version(1).stores({
      todo: 'tid'
    })

    this.todo = this.table('todo')
  }

  public add(todo: Partial<ToDo>): Promise<String> {
    todo.tid = uuidv4().toString().substring(0, 8)
    console.info('generated id: ', todo.tid)
    return this.todo.put(todo as ToDo)
  }

  public async getAllTitles(): Promise<string[]> {
    const t = await this.todo.toArray()
    return t.map(v => v.title)
  }

}

