package model;

import webapp.MainDispatcher;
import java.util.*;

import model.interfaces.IOrders;

import java.time.*;

public class Orders implements IOrders{
	private static final long serialVersionUID = 1L;
	private String ordersId;
	private Account account;
	private Map<String, Order> orders;

	public class Order {
		private String orderId;
		private CartItem cartItem;
		private String price;
		private Instant purchaseTime;
		public Order(CartItem ci) {
			this.cartItem = ci;
			this.price = ci.getItem().getPrice();
			this.purchaseTime = Instant.now();
			this.orderId = MainDispatcher.createRandomId();
		}
		public CartItem getCartItem() {
			return cartItem;
		}
		public String getOrderId() {
			return orderId;
		}
		public String getPrice() {
			return price;
		}
		public Instant getPurchaseTime() {
			return purchaseTime;
		}
	}

	public Orders(Account ac) {
		this.ordersId = ac.getUserId();
		this.account = ac;
		this.orders = new TreeMap<String, Order>();
	}

	public Map<String, Order> getOrders() {
		return orders;
	}

	public Order getOrder(String cartItemId) {
		return orders.get(cartItemId);
	}

	public void addOrder(CartItem ci) {
		orders.put(ci.getCartItemId(), new Order(ci));
	}

	public Account getAccount() {
		return this.account;
	}

	public String getOrdersId() {
		return this.ordersId;
	}
	
	public String getAccountId() {
		return getAccount().getUserId();
	}

	public void setAccount(Account account) {
		this.account = account;
	}
}