package ibf2021.paf.day32.models;

import org.springframework.jdbc.support.rowset.SqlRowSet;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

import java.io.ByteArrayInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;;

public class Task {
	private Integer id;
	private String username;
	private String taskName;
	private Priority priority;
	private Date dueDate;

	public void setId(Integer id) { this.id = id; }
	public Integer getId() { return this.id; }

	public void setUsername(String username) { this.username = username; }
	public String getUsername() { return this.username; }

	public void setTaskName(String taskName) { this.taskName = taskName; }
	public String getTaskName() { return this.taskName; }

	public void setPriority(Priority priority) { this.priority = priority; }
	public Priority getPriority() { return this.priority; }

	public void setDueDate(Date dueDate) { this.dueDate = dueDate; }
	public Date getDueDate() { return this.dueDate; }

	public static Task create(SqlRowSet rs) {
		final Task t = new Task();
		t.id = rs.getInt("tid");
		t.username = rs.getString("username");
		t.taskName = rs.getString("task_name");
		t.priority = Enum.valueOf(Priority.class, rs.getString("priority"));
		t.dueDate = rs.getDate("due_date");
		return t;
	}

	public static Task create(String jsonString) throws Exception {
		JsonReader r = Json.createReader(
				new ByteArrayInputStream(jsonString.getBytes()));
		JsonObject o = r.readObject();
		final Task todo = new Task();
		try {
			todo.id = o.getInt("id", -1);
		} catch (Exception ex) { }
		todo.username = o.getString("username");
		todo.taskName = o.getString("taskName");
		todo.priority = Enum.valueOf(Priority.class, o.getString("priority"));
		todo.dueDate = (new SimpleDateFormat("yyyy-MM-dd"))
				.parse(o.getString("dueDate"));

		return todo;
	}

	public JsonObject toJson() {
		return Json.createObjectBuilder()
			.add("id", id)
			.add("username", username)
			.add("taskName", taskName)
			.add("priority", priority.toString())
			.add("dueDate", dueDate.toGMTString())
			.build();
	}
}
