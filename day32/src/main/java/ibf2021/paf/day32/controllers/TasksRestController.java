package ibf2021.paf.day32.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ibf2021.paf.day32.repositories.TaskRepository;
import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;

@RestController
@RequestMapping(path="/api/tasks")
public class TasksRestController {

	@Autowired
	private TaskRepository taskRepo;

	@GetMapping
	public ResponseEntity<String> getTasks() {
		JsonArrayBuilder arrBuilder = Json.createArrayBuilder();
		taskRepo.getTasks().stream()
			.forEach(t -> arrBuilder.add(t.toJson()));
		return ResponseEntity.ok(arrBuilder.build().toString());
	}


}
