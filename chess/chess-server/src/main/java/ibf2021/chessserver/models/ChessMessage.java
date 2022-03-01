package ibf2021.chessserver.models;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import jakarta.json.JsonReader;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import static ibf2021.chessserver.Constants.*;

public class ChessMessage {

	private final String cmd;
	private final String gid;
	private String player;
	private String src;
	private String dst;

	public ChessMessage(String cmd, String gid) {
		this.cmd = cmd;
		this.gid = gid;
	}

	public String getGameId() { return this.gid; }
	public String getCommand() { return this.cmd; }

	public void setPlayer(String player) { this.player = player; }
	public String getPlayer() { return this.player; }

	public void setSrc(String src) { this.src = src; }
	public String getSrc() { return this.src; }

	public void setDst(String dst) { this.dst = dst; }
	public String getDst() { return this.dst; }

	public JsonObject toJson() {
		final JsonObjectBuilder builder = Json.createObjectBuilder();
		builder.add(MSG_ATTR_CMD, this.cmd);
		builder.add(MSG_ATTR_GID, this.gid);

		switch (cmd) {
			case CMD_NEW:
			case CMD_JOIN:
				break;

			case CMD_START: 
				builder.add(MSG_ATTR_PLAYER, this.player);
				break;

			case CMD_MOVE:
				builder.add(MSG_ATTR_PLAYER, this.player);
				builder.add(MSG_ATTR_SRC, src);
				builder.add(MSG_ATTR_DEST, dst);
				break;

			default:
				throw new IllegalArgumentException("Unknow message command: %s".formatted(this.cmd));

		}

		return builder.build();

	}
}
