package model;

import java.time.Instant;
import jakarta.persistence.*;
import webapp.MainDispatcher;
public class CartItem {
	private String itemId;
	private Instant listingTime;
	private Item item;
	private int itemCount;

	public CartItem(Item item) {
		if (item != null) {
			this.itemId = MainDispatcher.createRandomId();
			this.itemCount = 1;
			this.item = item;
			this.itemId = item.getItemId();
		} else {
			return;
		}
	}

	public Item getItem() {
		return item;
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

	public void setItem(Item item) {
		this.item = item;
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

}