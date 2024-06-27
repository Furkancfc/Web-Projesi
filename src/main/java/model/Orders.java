package model;

import webapp.MainDispatcher;
import java.util.*;

import io.micrometer.common.lang.NonNull;
import model.interfaces.IOrders;

import java.time.*;

public class Orders implements IOrders {
	private static final long serialVersionUID = 1L;
	private String ordersId;
	private Map<String, Order> orders;

	public class Order {
		private String orderId;
		private String price;
		private Instant purchaseTime;
		private CartItem cartItem;
		public Order(CartItem ci) {
			this.orderId = ci.getCartItemId();
			this.price = ci.getItem().getPrice();
			this.purchaseTime = Instant.now();
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

	public void addOrder(CartItem ci) {
		orders.put(ci.getCartItemId(), new Order(ci));
	}

	public Order removeOrder(String cartItemId) {
		return orders.remove(cartItemId);
	}

	public void setOrder(@NonNull Order o) {
		orders.put(o.getCartItem().getCartItemId(), o);
	}

}