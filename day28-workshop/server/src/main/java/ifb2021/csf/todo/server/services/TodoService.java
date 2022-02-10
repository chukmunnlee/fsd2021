package ifb2021.csf.todo.server.services;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Service;

import ifb2021.csf.todo.server.models.Todo;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import jakarta.json.JsonArray;

// Simplistic class. Only handles 1 todo list
// Not threadsafe

@Service
public class TodoService {

	private List<Todo> todos = new LinkedList<>();

	public JsonArray getTodosAsJsonArray() {
		List<JsonObject> jTodos = todos.stream()
				.map(t -> t.toJson())
				.toList();
		return Json.createArrayBuilder(jTodos).build();
	}

	public void update(String newTodos) throws IOException {
		try (Reader reader = new StringReader(newTodos)) {
			JsonReader jReader = Json.createReader(reader);
			this.update(jReader.readArray());
		} 
	}

	public void update(JsonArray newTodos) {
		this.todos = newTodos.stream()
			.map(j -> Todo.toTodo((JsonObject)j))
			.toList();
	}

}
