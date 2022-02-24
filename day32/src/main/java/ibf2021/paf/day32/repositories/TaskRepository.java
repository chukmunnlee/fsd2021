package ibf2021.paf.day32.repositories;

import java.util.List;
import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import ibf2021.paf.day32.models.Task;

import static ibf2021.paf.day32.repositories.SQLs.*;

@Repository
public class TaskRepository {

	@Autowired
	private JdbcTemplate template;

	public List<Task> getTasks() {
		List<Task> result = new LinkedList<>();
		final SqlRowSet rs = template.queryForRowSet(SQL_GET_ALL_TASKS);
		while (rs.next())
			result.add(Task.create(rs));
		return result;
	}

	public boolean insertTask(Task task) {
		return template.update(SQL_ADD_NEW_TASK, 
				task.getUsername(), task.getTaskName(),
				task.getPriority().toString(), task.getDueDate()) > 0;
	}
}
