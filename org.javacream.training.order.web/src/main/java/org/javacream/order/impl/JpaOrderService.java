package org.javacream.order.impl;

import org.javacream.order.api.Order;
import org.javacream.order.api.Order.OrderStatus;
import org.javacream.order.api.OrderService;
import org.javacream.order.books.BooksService;
import org.javacream.order.books.OrderBook;
import org.javacream.order.store.StoreService;
import org.javacream.util.SequenceGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JpaOrderService implements OrderService {

	@Autowired private OrderRepository orderRepository;
	@Autowired
	private BooksService booksService;
	@Autowired
	private StoreService storeService;
	@Autowired private SequenceGenerator sequenceGenerator;
	@Override

	public Order order(String isbn, int number) {
		OrderStatus orderStatus;
		double totalPrice = 0;
		if (isbn == null) {
			throw new IllegalArgumentException("isbn was null");
		}
		if (number <= 0) {
			throw new IllegalArgumentException("number must be poitive, was " + number);
		}
		int stock = 0;
		try {
			OrderBook book = booksService.findBookByIsbn(isbn);
			totalPrice = book.getPrice() * number;
			stock = storeService.getStock("BOOKS", isbn);
			if (stock < number) {
				orderStatus = OrderStatus.PENDING;
			} else {
				orderStatus = OrderStatus.OK;
			}
		}

		catch (Exception e) {
			orderStatus = OrderStatus.UNAVAILABLE;
		}
		
		Order order =  new Order(sequenceGenerator.nextKey(), isbn, totalPrice, orderStatus);
		if (orderStatus == OrderStatus.OK) {
			int newStock = stock - number;
			storeService.setStock("BOOKS", isbn, newStock);
			
			
		}
		orderRepository.save(order);
		return order;
	}
	@Override
	public Order findById(Long id) {
		return orderRepository.findById(id).get();
	}

}
