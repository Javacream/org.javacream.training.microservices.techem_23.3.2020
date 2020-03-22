package org.javacream.books.ordering.api;


public interface OrderService {
	Order order (String isbn, int number);
	Order findById(Long id);
}
