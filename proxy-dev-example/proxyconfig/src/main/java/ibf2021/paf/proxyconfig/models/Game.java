package ibf2021.paf.proxyconfig.models;

import org.springframework.jdbc.support.rowset.SqlRowSet;

import jakarta.json.Json;
import jakarta.json.JsonObject;

public class Game {
	private Integer gameId;
	private String name;
	private Integer year;
	private String image;

	public Integer getGameId() { return this.gameId; }
	public void setGameId(Integer gameId) { this.gameId = gameId; }

	public String getName() { return this.name; }
	public void setName(String name) { this.name = name; }

	public Integer getYear() { return this.year; }
	public void setYear(Integer year) { this.year = year; }

	public String getImage() { return this.image; }
	public void setImage(String image) { this.image = image; }

	public JsonObject toJson() {
		return Json.createObjectBuilder()
			.add("gameId", this.gameId)
			.add("name", this.name)
			.add("year", this.year)
			.add("image", this.image)
			.build();
	}

	public static Game populate(SqlRowSet rs) {
		final Game game = new Game();
		game.setGameId(rs.getInt("gid"));
		game.setName(rs.getString("name"));
		game.setYear(rs.getInt("year"));
		game.setImage(rs.getString("image"));
		return game;
	}

}
