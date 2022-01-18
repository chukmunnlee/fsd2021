package ibf2021.ssf.assessment.review.services;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import ibf2021.ssf.assessment.review.models.Book;

@Service
public class BookService {

	private final Logger logger = Logger.getLogger(BookService.class.getName());

	public static final String BASE = "http://openlibrary.org";
	public static final String SEARCH = "%s/search.json".formatted(BASE);

	public List<Book> search(String title) throws IOException {

		final String searchUrl = UriComponentsBuilder
				.fromUriString(SEARCH)
				.queryParam("title", title.trim().replace(" ", "+"))
				.queryParam("limit", 20)
				.queryParam("fields", "key,title")
				.toUriString();

		final RestTemplate template = new RestTemplate();
		final ResponseEntity<String> resp = template.getForEntity(searchUrl, String.class);

		try {
			return Book.toBookList(resp.getBody());
		} catch (final IOException ex) {
			logger.log(Level.WARNING, ex.getMessage(), ex);
			throw ex;
		}
	}

	public Book getBook(String work) throws IOException {

		final String bookUrl = "%s/works/%s.json".formatted(BASE, work);

		final RestTemplate template = new RestTemplate();
		final ResponseEntity<String> resp = template.getForEntity(bookUrl, String.class);

		try {
			return Book.toBook(resp.getBody());
		} catch (IOException ex) {
			logger.log(Level.WARNING, ex.getMessage(), ex);
			throw ex;
		}
	}
}
