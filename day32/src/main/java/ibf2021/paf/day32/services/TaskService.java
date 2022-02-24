package ibf2021.paf.day32.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import ibf2021.paf.day32.models.Task;
import ibf2021.paf.day32.repositories.TaskRepository;
import ibf2021.paf.day32.repositories.UserRepository;

@Service
public class TaskService {

	@Autowired
	private TaskRepository taskRepo;

	@Autowired
	private UserRepository userRepo;

	public Optional<Integer> addTask(Task task) {

		if (!userRepo.hasUser(task.getUsername()))
			return Optional.of(401); //should have proper error codes

		if (taskRepo.insertTask(task))
			return Optional.empty();

		return Optional.of(400);
	}


}
