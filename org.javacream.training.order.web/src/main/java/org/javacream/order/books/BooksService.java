package org.javacream.order.books;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class BooksService {

	@Autowired
	@Qualifier("booksService")
	private RestTemplate restTemplate;

	public OrderBook findBookByIsbn(String isbn) {
		return restTemplate.getForObject("http://localhost:8088/api/books/" + isbn, OrderBook.class);

	}

}