package model;

import webapp.MainDispatcher;
import jakarta.persistence.*;
import java.util.*;

import model.interfaces.*;
public class Category implements ICategory {

	private static final long serialVersionUID = 1L;
	private String name;
	private String categoryId;
	private int itemCount;
	private Map<String, Item> itemsIds;

	public Category(String name) {
		this.name = name;
		this.categoryId = MainDispatcher.createRandomId();
		this.itemsIds = new HashMap<String, Item>();
	}

	// getters
	public String getName() {
		return name;
	}

	public int getItemCount() {
		return itemCount;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public Collection<Item> getItems() {
		return itemsIds.values();
	}

	public Item getItem(String itemId) {
		return itemsIds.get(itemId);
	}

	// setters
	public void setName(String name) {
		this.name = name;
	}

	public void setCategoryId(String id) {
		this.categoryId = id;
	}

	public void setItemCount(int count) {
		this.itemCount = count;
	}

	public void setItemIds(Map<String, Item> items) {
		this.itemsIds = items;
	}

	public void addItem(Item item) {
		if (item != null) {
			this.itemsIds.put(item.getItemId(), item);
		} else {
			System.err.println("Item cannot be null");
		}
	}

}
