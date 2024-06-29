package model;

import java.net.URLEncoder;
import java.time.Instant;

import model.interfaces.IOrder;
import webapp.MainDispatcher;

public class Order implements IOrder {
	private String paymentMethod;
	private String orderId;
	private Instant purchaseTime;
	private CartItem cartItem;
	private Double price;

	public Order(CartItem ci, Double price, String paymentMethod) {
		this.cartItem = ci;
		this.orderId = MainDispatcher.createRandomId();
		this.purchaseTime = Instant.now();
		this.price = price;
		this.paymentMethod = paymentMethod;
	}

	public CartItem getCartItem() {
		return cartItem;
	}

	public String getOrderId() {
		return orderId;
	}

	public Double getPrice() {
		return price;
	}

	public Instant getPurchaseTime() {
		return purchaseTime;
	}

	public String getURL() {
		return URLEncoder.encode(orderId);
	}
}
