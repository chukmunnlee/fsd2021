export interface TodoSummary {
	tid: string
	title: string
}

export interface Todo extends TodoSummary {
	description: string
	priority: string
}

export interface TodoGuard {
	evaluate(): boolean
}

export class TodoImpl implements Todo {

	tid!: string
	title!: string
	description!: string
	priority!: string

  constructor() { }

}
