package ibf2021.paf.day32.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ibf2021.paf.day32.models.Task;
import ibf2021.paf.day32.repositories.TaskRepository;
import ibf2021.paf.day32.repositories.UserRepository;
import ibf2021.paf.day32.services.TaskService;
import jakarta.json.Json;
import jakarta.json.JsonObject;

@RestController
@RequestMapping(path="/api/task")
public class TaskRestController {

	@Autowired
	private TaskService taskSvc;

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> postTask(@RequestBody String payload) {

		System.out.println(">>>> payload: " + payload);

		Task task = null;
		JsonObject err;
		try {
			task = Task.create(payload);
		} catch (Exception ex) {
			System.out.println(">>>>> getMessage: " + ex.getMessage());
			err = Json.createObjectBuilder()
				.add("error", ex.getMessage())
				.build();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(err.toString());
		}

		Optional<Integer> opt = taskSvc.addTask(task);

		if (opt.isEmpty())
			return ResponseEntity.ok("{}");

		switch (opt.get()) {
			case 401:
				err = Json.createObjectBuilder()
					.add("error", "Username %s not found")
					.build();
				return ResponseEntity.status(HttpStatus.FORBIDDEN)
					.body(err.toString());

			default:
				err = Json.createObjectBuilder()
					.add("error", "Failed to create new task")
					.build();
				return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(err.toString());
		}

	}
}

