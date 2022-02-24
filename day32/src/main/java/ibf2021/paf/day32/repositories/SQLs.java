package ibf2021.paf.day32.repositories;

public interface SQLs {
	public static final String SQL_GET_ALL_TASKS = 
			"select * from task order by username";

	public static final String SQL_GET_USER_BY_USERNAME =
			"select count(*) user_count from user where username = ?";

	public static final String SQL_ADD_NEW_TASK = 
			"insert into task(username, task_name, priority, due_date) values (?, ?, ?, ?)";
}
