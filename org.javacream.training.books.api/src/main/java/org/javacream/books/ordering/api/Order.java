package org.javacream.books.ordering.api;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@Entity(name = "OrderEntity")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Order {
	@Id
	private long orderId;
	private String isbn;
	public void setStatus(OrderStatus status) {
		this.status = status;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((isbn == null) ? 0 : isbn.hashCode());
		result = prime * result + (int) (orderId ^ (orderId >>> 32));
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		long temp;
		temp = Double.doubleToLongBits(totalPrice);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		if (isbn == null) {
			if (other.isbn != null)
				return false;
		} else if (!isbn.equals(other.isbn))
			return false;
		if (orderId != other.orderId)
			return false;
		if (status != other.status)
			return false;
		if (Double.doubleToLongBits(totalPrice) != Double.doubleToLongBits(other.totalPrice))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", isbn=" + isbn + ", totalPrice=" + totalPrice + ", status=" + status
				+ "]";
	}


	public Order(long orderId, String isbn, double totalPrice, OrderStatus status) {
		super();
		this.orderId = orderId;
		this.isbn = isbn;
		this.totalPrice = totalPrice;
		this.status = status;
	}


	public long getOrderId() {
		return orderId;
	}


	public String getIsbn() {
		return isbn;
	}


	public double getTotalPrice() {
		return totalPrice;
	}


	public OrderStatus getStatus() {
		return status;
	}


	private double totalPrice;
	private OrderStatus status;

	Order() {}
	
	public static enum OrderStatus{
		OK, PENDING, UNAVAILABLE;
	}
}
