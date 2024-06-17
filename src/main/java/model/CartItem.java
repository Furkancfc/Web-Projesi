package model;

import java.time.Instant;
import jakarta.persistence.*;
public class CartItem {
	private Instant listingTime;
	private String itemId;
	private Item item;
	private int itemCount;

	public CartItem(Item item) {
		if (item != null) {
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

	public void increment() {
		this.itemCount += 1;
	}

	public void decrement() {
		this.itemCount -= 1;
	}

}