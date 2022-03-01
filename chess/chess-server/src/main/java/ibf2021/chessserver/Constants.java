package ibf2021.chessserver;

public interface Constants {
	public static final String ATTR_GAMEID = "gameId";
	public static final String ATTR_ORIENTATION = "orientation";

	public static final String MSG_ATTR_CMD = "command";
	public static final String MSG_ATTR_GID = "gameId";
	public static final String MSG_ATTR_SRC = "src";
	public static final String MSG_ATTR_DEST = "dest";
	public static final String MSG_ATTR_PLAYER = "player";

	public static final String ORIENTATION_WHITE = "white";
	public static final String ORIENTATION_BLACK = "black";

	public static final String CMD_NEW = "new";
	public static final String CMD_JOIN = "join";
	public static final String CMD_START = "start";
	public static final String CMD_MOVE = "move";
}
