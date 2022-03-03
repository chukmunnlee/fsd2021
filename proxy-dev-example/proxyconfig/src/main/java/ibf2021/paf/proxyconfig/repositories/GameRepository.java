package ibf2021.paf.proxyconfig.repositories;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import ibf2021.paf.proxyconfig.models.Game;

@Repository
public class GameRepository {

	private static final String SQL_SELECT_GAMES_LIMIT_OFFSET =
		"select gid, name, year, image from game limit ? offset ?";

	@Autowired
	private JdbcTemplate template;

	public List<Game> listGames(Integer limit, Integer offset) {
		final List<Game> games = new LinkedList<>();

		final SqlRowSet rs = template.queryForRowSet(
				SQL_SELECT_GAMES_LIMIT_OFFSET, limit, offset);
		while (rs.next())
			games.add(Game.populate(rs));

		return games;
	}

}
