package ifb2021.csf.todo.server.controllers;

import java.io.IOException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ifb2021.csf.todo.server.services.TodoService;
import jakarta.json.Json;
import jakarta.json.JsonObject;

@RestController
@RequestMapping(path="/api/todos", produces=MediaType.APPLICATION_JSON_VALUE)
public class TodosRestController {

	@Autowired
	private TodoService todoSvc;

	@GetMapping
	public ResponseEntity<String> getTodos() {
		return ResponseEntity.ok(todoSvc.getTodosAsJsonArray().toString());
	}

	@PostMapping(consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> postTodos(@RequestBody String payload) {
		try {
			this.todoSvc.update(payload);
			JsonObject ok = Json.createObjectBuilder()
				.add("message", "Update on %s".formatted(new Date()))
				.build();
			return ResponseEntity.status(HttpStatus.CREATED).body(ok.toString());
		} catch (IOException ex) {
			JsonObject err = Json.createObjectBuilder()
				.add("error", ex.getMessage())
				.build();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err.toString());
		}
	}
}
