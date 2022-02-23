package ibf2021.psf.day31.repositories;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import ibf2021.psf.day31.models.Book;

import static ibf2021.psf.day31.repositories.SQL.*;

@Repository
public class GoodreadRepository {

    @Autowired
    private JdbcTemplate template;

    // return limit records from 0
    public List<Book> getAllBooks(int limit) {
        return getAllBooks(0, limit);
    }

    // return limit records from offset
    public List<Book> getAllBooks(int offset, int limit) {
        final List<Book> books = new LinkedList<>();

        // return all books
        final SqlRowSet rs = template.queryForRowSet(
                SQL_GET_ALL_BOOKS_BY_LIMIT_OFFSET, limit, offset);
        while (rs.next()) {
            // Process a row
            final Book book = Book.populate(rs);
            books.add(book);
        }

        return books;
    }

    public List<Book> getAllBooks() {

        final List<Book> books = new LinkedList<>();

        // return all books
        final SqlRowSet rs = template.queryForRowSet(SQL_GET_ALL_BOOKS);
        // loop thru row set 
        while (rs.next()) {
            // Process a row
            final Book book = Book.populate(rs);
            books.add(book);
        }
        return books;
    }

    public List<Book> getBooksByTitle(String phrase) {

        final List<Book> books = new LinkedList<>();

        final String search = "%" + phrase + "%";
        final SqlRowSet rs = template.queryForRowSet(
            SQL_GET_BOOKS_BY_TITLE,  // prepared statement
            search // parameters
        );
        // loop thru row set 
        while (rs.next()) {
            // Process a row
            final Book book = Book.populate(rs);
            books.add(book);
        }
        return books;
    }

    public List<String> getBooksFormat() {
        final List<String> result = new LinkedList<>();
        final SqlRowSet rs = template.queryForRowSet(SQL_GET_BOOKS_FORMAT);

        while (rs.next())
            result.add(rs.getString("format").toLowerCase());

        return result;
    }
    
}
