package model;

import java.net.URLEncoder;
import java.time.Instant;
import jakarta.persistence.*;
import model.interfaces.ICartItem;
import webapp.MainDispatcher;

public class CartItem implements ICartItem {
	private String cartId;
	private String itemId;
	private Instant listingTime;
	private int itemCount;

	public CartItem(String itemId, String cartId) {
		if (itemId != null) {
			this.cartId = cartId;
			this.itemCount = 1;
			this.itemId = itemId;
		} else {
			return;
		}
	}

	public String getItemId() {
		return itemId;
	}

	public int getItemCount() {
		return itemCount;
	}

	public Instant getListingTime() {
		return listingTime;
	}

	public String getCartItemId() {
		return itemId;
	}

	public void setListingTime(Instant listingTime) {
		this.listingTime = listingTime;
	}

	public void setItemCount(int itemCount) {
		this.itemCount = itemCount;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public void setCartItemId(String cartItemId) {
		this.itemId = cartItemId;
	}

	public void increment() {
		this.itemCount += 1;
	}

	public void decrement() {
		this.itemCount -= 1;
	}

	public String getCartId() {
		return cartId;
	}

	public void setCartId(String cartId) {
		this.cartId = cartId;
	}

	public String getURL() {
		return URLEncoder.encode(getCartItemId());
	}

}