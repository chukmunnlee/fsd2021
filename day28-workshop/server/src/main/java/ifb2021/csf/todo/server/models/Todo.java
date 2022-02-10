package ifb2021.csf.todo.server.models;

import jakarta.json.Json;
import jakarta.json.JsonObject;

public class Todo {
	private String tid;
	private String title;
	private String description;
	private String priority;

	public Todo() { }
	public Todo(String tid) { this.tid = tid; }

	public String getId() { return this.tid; }
	public void setId(String tid) { this.tid = tid; }

	public String getTitle() { return this.title; }
	public void setTitle(String title) { this.title = title; }

	public String getDescription() { return this.description; }
	public void setDescription(String description) { this.description = description; }

	public String getPriority() { return this.priority; }
	public void setPriority(String priority) { this.priority = priority; }

	@Override
	public String toString() {
		return "tid: %s, title: %s".formatted(tid, title);
	}

	public static Todo toTodo(JsonObject j) {
		final Todo todo = new Todo(j.getString("tid"));
		todo.setTitle(j.getString("title"));
		todo.setDescription(j.getString("description"));
		todo.setPriority(j.getString("priority"));
		return todo;
	}

	public JsonObject toJson() {
		return Json.createObjectBuilder()
			.add("tid", tid)
			.add("title", title)
			.add("description", description)
			.add("priority", priority)
			.build();
	}
}
