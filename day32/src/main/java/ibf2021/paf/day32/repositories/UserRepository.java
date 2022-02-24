package ibf2021.paf.day32.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import static ibf2021.paf.day32.repositories.SQLs.*;

@Repository
public class UserRepository {

	@Autowired
	private JdbcTemplate template;

	public boolean hasUser(String username) {
		final SqlRowSet rs = template.queryForRowSet(
				SQL_GET_USER_BY_USERNAME, username);
		if (rs.next())
			return rs.getInt("user_count") > 0;
		return false;
	}
}
