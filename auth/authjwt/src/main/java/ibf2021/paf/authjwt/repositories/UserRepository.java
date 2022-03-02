package ibf2021.paf.authjwt.repositories;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import ibf2021.paf.authjwt.models.User;

@Repository
public class UserRepository {

	private static final String SQL_SELECT_USER_BY_USERNAME =
		"select * from user where username = ?";

	private static final String SQL_COMPARE_PASSWORDS_BY_USERNAME = 
		"select count(*) as user_count from user where username = ? and password = sha1(?)";

	@Autowired
	private JdbcTemplate template;

	public Optional<User> findUserByName(String username) {
		final SqlRowSet rs = template.queryForRowSet(SQL_SELECT_USER_BY_USERNAME, username);
		if (rs.next())
			return Optional.of(User.populate(rs));
		return Optional.empty();
	}

	public boolean validateUser(String username, String password) {
		final SqlRowSet rs = template.queryForRowSet(SQL_COMPARE_PASSWORDS_BY_USERNAME, username, password);
		if (!rs.next())
			return false;

		return rs.getInt("user_count") > 0;
	}
}

