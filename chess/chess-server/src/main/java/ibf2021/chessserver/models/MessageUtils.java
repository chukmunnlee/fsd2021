package ibf2021.chessserver.models;

import java.io.IOException;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

import static ibf2021.chessserver.Constants.*;

import java.io.ByteArrayInputStream;

public class MessageUtils {

	public static ChessMessage createStartGame(String gameId, String player) {
		final ChessMessage msg = new ChessMessage(CMD_START, gameId);
		msg.setPlayer(player);
		return msg;
	}

	public static ChessMessage createNewGame(String gid) {
		final ChessMessage msg = new ChessMessage(CMD_NEW, gid);
		return msg;
	}

	/* {"command":"join","gameId":"0cc4e339"}  */
	public static ChessMessage parse(String msg) throws IOException {
		final JsonReader reader = Json.createReader(new ByteArrayInputStream(msg.getBytes()));
		final JsonObject obj = reader.readObject();
		final String cmd = obj.getString(MSG_ATTR_CMD);
		final String gameId = obj.getString(MSG_ATTR_GID);

		ChessMessage chessMsg = new ChessMessage(cmd, gameId);

		switch (cmd) {
			case CMD_JOIN:
				break;

			case CMD_MOVE:
				chessMsg.setSrc(obj.getString(MSG_ATTR_SRC));
				chessMsg.setDst(obj.getString(MSG_ATTR_DEST));
				chessMsg.setPlayer(obj.getString(MSG_ATTR_PLAYER));
				break;

			default:
		}

		return chessMsg;

	}
}
