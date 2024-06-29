package model;

import webapp.MainDispatcher;
import java.util.*;

import io.micrometer.common.lang.NonNull;
import model.interfaces.IOrders;

import java.net.URLEncoder;
import java.time.*;

public class Orders implements IOrders {
	private static final long serialVersionUID = 1L;
	private String ordersId;
	private Map<String, Order> orders;
	public class Order {
		private String orderId;
		private Instant purchaseTime;
		private CartItem cartItem;
		private String price;

		public Order(CartItem ci, String price) {
			this.orderId = ci.getCartItemId();
			this.purchaseTime = Instant.now();
			this.price = price;
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

	public Orders(String userId) {
		this.ordersId = userId;
		this.orders = new TreeMap<String, Order>();
	}

	public void setOrders(Map<String, Order> orders) {
		this.orders = orders;
	}

	public Map<String, Order> getOrders() {
		return orders;
	}

	public void setOrdersId(String ordersId) {
		this.ordersId = ordersId;
	}

	public String getOrdersId() {
		return this.ordersId;
	}

	public Order getOrder(String cartItemId) {
		return orders.get(cartItemId);
	}

	public void addOrder(CartItem ci,String price) {
		orders.put(ci.getCartItemId(), new Order(ci, price));
	}

	public Order removeOrder(String cartItemId) {
		return orders.remove(cartItemId);
	}

	public void setOrder(@NonNull Order o) {
		orders.put(o.getCartItem().getCartItemId(), o);
	}

	public String getURL() {
		return URLEncoder.encode(getOrdersId());
	}

}