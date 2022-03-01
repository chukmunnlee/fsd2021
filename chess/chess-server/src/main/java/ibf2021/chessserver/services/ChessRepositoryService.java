package ibf2021.chessserver.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import org.springframework.stereotype.Service;
import org.springframework.web.socket.WebSocketSession;

import ibf2021.chessserver.models.Game;
import jakarta.json.JsonObject;
import jakarta.json.JsonValue;

import ibf2021.chessserver.models.ChessMessage;

import static ibf2021.chessserver.Constants.*;
import static ibf2021.chessserver.models.MessageUtils.*;

@Service
public class ChessRepositoryService {

	final private Map<String, Game> games = new HashMap<>();
	final private ReadWriteLock rwLock = new ReentrantReadWriteLock(true);

	public ChessRepositoryService() { }

	public String createGame(final WebSocketSession sess) {
		String gid = writeLock(() -> {
			final Game game = new Game();
			Map<String, Object> attr = sess.getAttributes();
			attr.put(ATTR_GAMEID, game.getGameId());
			attr.put(ATTR_ORIENTATION, ORIENTATION_WHITE);
			System.out.printf(">>> create game: %s\n", attr.get(ATTR_ORIENTATION).toString());
			game.addPlayer(sess);
			games.put(game.getGameId(), game);
			return game.getGameId();
		});
		return gid;
	}

	public boolean joinGame(final String gameId, final WebSocketSession sess) {
		final boolean result = writeLock(gameId, g -> {
			Map<String, Object> attr = sess.getAttributes();
			attr.put(ATTR_GAMEID, gameId);
			attr.put(ATTR_ORIENTATION, ORIENTATION_BLACK);
			addPlayerToGame(gameId, sess);
			return true;
		});
		return result;
	}

	public void startGame(final String gid) {
		readLock(gid, g -> {
			// Send start game to 
			ChessMessage chessMsg = createStartGame(gid, ORIENTATION_WHITE);
			sendMessage(gid, ORIENTATION_WHITE, chessMsg);

			chessMsg = createStartGame(gid, ORIENTATION_BLACK);
			sendMessage(gid, ORIENTATION_BLACK, chessMsg);
		});
	}

	public void sendMessage(final String gid, final ChessMessage payload) {
		readLock(gid, g -> {
			g.sendMessage(payload.toJson().toString());
		});
	}

	public void sendMessage(final String gid, final String player, final ChessMessage payload) {
		readLock(gid, g -> {
			g.sendMessage(player, payload.toJson().toString());
		});
	}

	public void deleteGame(final String gid) {
		writeLock(() -> {
			final Game g = games.remove(gid);
			if (null == g)
				return;
			g.close();
		});
	}

	public void addPlayerToGame(final String gid, final WebSocketSession sess) {
		writeLock(gid, g -> {
			g.addPlayer(sess);
		});
	}

	public List<String> getOpenGames() {
		return readLock(() -> {
			return games.values().stream()
				.filter(g -> !g.isStarted())
				.map(g -> g.getGameId())
				.toList();
		});
	}

	private <T> T readLock(Supplier<T> supp) {
		final Lock lock = rwLock.readLock();
		try {
			lock.lock();
			return supp.get();
		} finally {
			lock.unlock();
		}
	}

	private <R> R readLock(final String gid, Function<Game, R> func) {
		final Lock lock = rwLock.readLock();
		try {
			lock.lock();
			Game g = games.get(gid);
			if (null == g)
				return null;
			return func.apply(g);
		} finally {
			lock.unlock();
		}
	}

	private void readLock(final String gid, Consumer<Game> func) {
		final Lock lock = rwLock.readLock();
		try {
			lock.lock();
			Game g = games.get(gid);
			if (null == g)
				return;
			func.accept(g);
		} finally {
			lock.unlock();
		}
	}

	private <R> R writeLock(final Supplier<R> supp) {
		final Lock lock = rwLock.writeLock();
		try {
			lock.lock();
			return supp.get();
		} finally {
			lock.unlock();
		}
	}

	private <R> R writeLock(final String gid, Function<Game, R> func) {
		final Lock lock = rwLock.writeLock();
		try {
			lock.lock();
			Game g = games.get(gid);
			if (null == g)
				return null;
			return func.apply(g);
		} finally {
			lock.unlock();
		}
	}

	private void writeLock(final Runnable command) {
		final Lock lock = rwLock.writeLock();
		try {
			lock.lock();
			command.run();
		} finally {
			lock.unlock();
		}
	}

	private void writeLock(final String gid, Consumer<Game> func) {
		final Lock lock = rwLock.writeLock();
		try {
			lock.lock();
			Game g = games.get(gid);
			if (null == g)
				return;
			func.accept(g);
		} finally {
			lock.unlock();
		}
	}


}
