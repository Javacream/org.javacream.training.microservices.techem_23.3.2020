package org.javacream.books.ordering.impl;

import org.javacream.books.ordering.api.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long>{

}
