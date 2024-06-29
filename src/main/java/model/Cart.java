package model;

import java.net.URLEncoder;
import java.time.*;
import java.util.*;

import model.interfaces.ICart;

public class Cart implements ICart {

	private static final long serialVersionUID = 1L;
	private String cartId;
	private Map<String, CartItem> items;
	public Instant lastUpdate;

	public void setCartId(String cartId) {
		this.cartId = cartId;
	}

	public void setItems(Map<String, CartItem> items) {
		this.items = items;
	}

	public void setLastUpdate(Instant lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public Instant getLastUpdate() {
		return lastUpdate;
	}

	public Cart(String userId) {
		this.lastUpdate = Instant.now();
		this.items = new TreeMap<String, CartItem>();
		this.cartId = userId;
	}

	public Collection<CartItem> getItems() {
		return items.values();
	}

	public Set<String> getItemIds() {
		return items.keySet();
	}

	public CartItem deleteItem(String itemId) {
		return this.items.remove(itemId);
	}

	public CartItem addItem(CartItem item) {
		CartItem c;
		if ((c = this.items.get(item.getItemId())) != null) {
			c.increment();
			return c;
		}
		return this.items.put(item.getItemId(), item);
	}

	public CartItem getItem(String itemId) {
		return this.items.get(itemId);
	}

	public void incrementItem(String itemId) {
		try {
			this.items.get(itemId).increment();
		} catch (Exception e) {
			;
		}
	}

	public void decrementItem(String itemId) {
		try {
			this.items.get(itemId).decrement();
		} catch (Exception e) {
			;
		}
	}

	public void setCount(String itemId, int count) {
		try {
			this.items.get(itemId).setItemCount(count);
		} catch (Exception e) {
			;
		}
	}

	public String getCartId() {
		return this.cartId;
	}

	public String getURL() {
		return URLEncoder.encode(getCartId());
	}
}
