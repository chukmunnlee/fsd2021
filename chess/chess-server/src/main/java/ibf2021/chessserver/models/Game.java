package ibf2021.chessserver.models;

import java.io.IOException;
import java.util.LinkedList;
import java.util.UUID;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static ibf2021.chessserver.Constants.*;

public class Game {

	private final String gid;
	private List<WebSocketSession> players = new LinkedList<>();


	public Game() {
		gid = UUID.randomUUID().toString().substring(0, 8);
	}
	public Game(String gid) {
		this.gid = gid;
	}

	public String getGameId() { return this.gid; }

	public void addPlayer(WebSocketSession sess) {
		this.players.add(sess);
	}

	public boolean isStarted() {
		return (this.players.size() >= 2);
	}

	public void close() {
		players.stream()
			.forEach(sess -> {
				try { sess.close(); } catch (Exception ex) { }
			});
	}

	public Optional<WebSocketSession> getPlayer(final String player) {
		for (WebSocketSession sess: players) {
			Map<String, Object> attr = sess.getAttributes();
			if (attr.get(ATTR_ORIENTATION).toString().equals(player))
				return Optional.of(sess);
		}
		return Optional.empty();
	}

	public void sendMessage(final String player, final String payload) {
		Optional<WebSocketSession> opt = getPlayer(player);
		if (opt.isEmpty())
			return;
		WebSocketSession sess = opt.get();
		try {
			sess.sendMessage(new TextMessage(payload));
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public void sendMessage(final String payload) {
		final TextMessage msg = new TextMessage(payload);
		players.forEach(p -> {
			try {
				p.sendMessage(msg);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		});
	}

}
