package ibf2021.ssf.assessment.review.controllers;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import ibf2021.ssf.assessment.review.models.Book;
import ibf2021.ssf.assessment.review.repositories.BookRepository;
import ibf2021.ssf.assessment.review.services.BookService;

@Controller
@RequestMapping(path = "/book")
public class BookController {

	@Autowired
	private BookService bookSvc;

	@Autowired
	private BookRepository bookRepo;

	@GetMapping(path = "/{work}")
	public String getBook(Model model, @PathVariable(name="work") String work) {

		Optional<Book> opt = bookRepo.get(work);
		Book book = null;

		if (opt.isPresent()) {
			book = opt.get();
			book.setCached(true);

		} else
			try {
				book = bookSvc.getBook(work);
				bookRepo.set(book);
			} catch (IOException ex) {
				model.addAttribute("error", ex.getMessage());
			}

		model.addAttribute("book", book);

		return "book-details";
	}

}
