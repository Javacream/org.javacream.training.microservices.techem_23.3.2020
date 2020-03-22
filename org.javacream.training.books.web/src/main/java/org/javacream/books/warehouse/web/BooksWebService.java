package org.javacream.books.warehouse.web;

import java.util.Collection;

import org.javacream.books.warehouse.api.Book;
import org.javacream.books.warehouse.api.BookException;
import org.javacream.books.warehouse.api.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@RestController
@RequestMapping(path = "api")
public class BooksWebService {

	@Autowired
	private BooksService booksService;

	@PostMapping(path = "books")
	public String newBook(@RequestParam("title") String title) throws BookException {
		return booksService.newBook(title);
	}

	@GetMapping(path = "books/{isbn}")
	public Book findBookByIsbn(@PathVariable("isbn") String isbn) {

		try {
			return booksService.findBookByIsbn(isbn);
		} catch (BookException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping(path = "books", consumes = "application/json")
	public Book updateBook(@RequestBody Book book) throws BookException {
		return booksService.updateBook(book);
	}

	@DeleteMapping(path = "books/{isbn}")
	public void deleteBookByIsbn(@PathVariable("isbn") String isbn) throws BookException {
		booksService.deleteBookByIsbn(isbn);
	}

	public Collection<Book> findAllBooks() {
		return booksService.findAllBooks();
	}

}
