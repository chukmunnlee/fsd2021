package ibf2021.chessserver.controllers;

import java.net.URI;
import java.util.Date;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import ibf2021.chessserver.ChessserverApplication;
import ibf2021.chessserver.models.ChessMessage;
import ibf2021.chessserver.services.*;

import static ibf2021.chessserver.Constants.*;
import static ibf2021.chessserver.models.MessageUtils.*;

public class ChessEndpoint extends TextWebSocketHandler {

	private final Logger logger = Logger.getLogger(ChessserverApplication.class.getName());

	private final ChessRepositoryService chessRepoSvc;

	public ChessEndpoint(ChessRepositoryService chessRepoSvc) {
		this.chessRepoSvc = chessRepoSvc;
	}

	@Override
	public void afterConnectionEstablished(WebSocketSession sess) throws Exception {
		final URI uri = sess.getUri();

		if (isNewGame(uri)) {
			String gid = chessRepoSvc.createGame(sess);

			try {
				TextMessage msg = new TextMessage(createNewGame(gid).toJson().toString());
				sess.sendMessage(msg);
				logger.info("Creating new game: %s".formatted(gid));
			} catch(Exception ex) {
				logger.log(Level.SEVERE, "afterConnectionEstablished", ex);
			}
		} 
	}

	@Override
	public void afterConnectionClosed(WebSocketSession sess, CloseStatus status) throws Exception {
		Map<String, Object> attr = sess.getAttributes();
		String gid = (String)attr.get(ATTR_GAMEID);
		if (null == gid)
			return;
		chessRepoSvc.deleteGame(gid);
		logger.info("Delete game: %s".formatted(gid));
	}

	@Override
	public void handleMessage(WebSocketSession sess, WebSocketMessage<?> msg) throws Exception {
		System.out.printf("url path: %s\n", sess.getUri().getPath());
		System.out.printf("\tpayload: %s\n", msg.getPayload().toString());

		ChessMessage chessMsg = parse(msg.getPayload().toString());
		String gid = chessMsg.getGameId();

		switch (chessMsg.getCommand()) {
			case CMD_JOIN:
				chessRepoSvc.joinGame(gid, sess);
				// Confirm joined
				TextMessage resp = new TextMessage(msg.getPayload().toString());
				sess.sendMessage(resp);

				// Send start game to 
				chessRepoSvc.startGame(gid);
				break;

			case CMD_MOVE:
				chessRepoSvc.sendMessage(gid, chessMsg);
				break;

			default:
				logger.warning("Invalid command: %s".formatted(msg.getPayload().toString()));
		}
	}

	private boolean isNewGame(final URI uri) {
		String[] terms = uri.getPath().substring(1).split("/");
		return terms.length == 1;
	}
}
