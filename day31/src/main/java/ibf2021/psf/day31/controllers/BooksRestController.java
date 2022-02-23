package ibf2021.psf.day31.controllers;

import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ibf2021.psf.day31.models.Book;
import ibf2021.psf.day31.repositories.GoodreadRepository;
import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;

import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping(path="/api/books", produces = MediaType.APPLICATION_JSON_VALUE)
public class BooksRestController {

    @Autowired
    private GoodreadRepository goodreadsRepo;

    // GET /books?offset=number&limit=number
    @GetMapping
    public ResponseEntity<String> getAllBooks(
        @RequestParam(defaultValue = "10") Integer limit,
        @RequestParam(defaultValue = "0") Integer offset) {

        List<Book> result = goodreadsRepo.getAllBooks(offset, limit);
        JsonArrayBuilder arrBuilder = Json.createArrayBuilder();
        result.stream().forEach(v -> arrBuilder.add(v.toJson()));

        return ResponseEntity.ok(arrBuilder.build().toString());
    }

    // GET /api/books/format -> value from the format field
    @GetMapping(path="format")
    public ResponseEntity<String> getBooksFormat() {
        JsonArrayBuilder arrBuilder = Json.createArrayBuilder();
        goodreadsRepo.getBooksFormat().stream()
            .forEach(arrBuilder::add);

        return ResponseEntity.ok(arrBuilder.build().toString());
    }

    // GET /api/books/count/<format>
    // { <format>: <count> }
    // GET /api/books/count/ebook -> { "ebook": 20 }

    
    
}
