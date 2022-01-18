package ibf2021.ssf.assessment.review.repositories;

import java.io.IOException;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import ibf2021.ssf.assessment.review.models.Book;
import jakarta.json.JsonObject;

@Repository 
public class BookRepository {

	private final Logger logger = Logger.getLogger(BookRepository.class.getName());

	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	public Optional<Book> get(String key) {
		Object book = redisTemplate.opsForValue().get(key);
		try {
			if (null != book) 
				return Optional.of(Book.toBook(book.toString()));
		} catch (IOException ex) {
			logger.log(Level.WARNING, ex.getMessage(), ex);
		}
		return Optional.empty();
	}

	public void set(Book book) {
		JsonObject o = book.toJson();
		redisTemplate.opsForValue().set(book.getKey(), o.toString(), 300L, TimeUnit.SECONDS);
	}
}
