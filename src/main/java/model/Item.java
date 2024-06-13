package model;

import java.time.*;
import jakarta.persistence.*;

import model.interfaces.IItem;
import webapp.MainDispatcher;

public class Item implements IItem {
	private static final long serialVersionUID = 1L;
	private String itemId;
	private String title;
	private String shortDesc;
	private String longDesc;
	private String[] imageURIs;
	private Instant createTime; // create time
	private Instant lastUpdate; // write access
	private Instant lastAccess; // read access

	public Item(String title, String shortDesc, String longDesc) {
		this.itemId = MainDispatcher.createRandomId();
		if (title == null) {
			System.err.println("Title cannot be null");
			return;
		}
		this.title = title;
		this.shortDesc = shortDesc;
		this.longDesc = longDesc;
		this.imageURIs = null;
		this.createTime = Instant.now();
		this.lastUpdate = Instant.now();
		this.lastAccess = null;
	}

	// getters
	public String[] getImages() {
		this.lastAccess = Instant.now();
		return imageURIs;
	}

	public Instant getCreateTime() {
		this.lastAccess = Instant.now();
		return createTime;
	}

	public String getItemId() {
		this.lastAccess = Instant.now();
		return itemId;
	}

	public String getShortDesc() {
		this.lastAccess = Instant.now();
		return shortDesc;
	}

	public String getLongDesc() {
		this.lastAccess = Instant.now();
		return longDesc;
	}

	public Instant getLastAccess() {
		this.lastAccess = Instant.now();
		return lastAccess;
	}

	public Instant getLastUpdate() {
		this.lastAccess = Instant.now();
		return lastUpdate;
	}

	public String getTitle() {
		this.lastAccess = Instant.now();
		return title;
	}

	// setters
	public void setImageURI(String[] imageURIs) {
		this.lastUpdate = Instant.now();
		this.imageURIs = imageURIs;
	}

	public void setLongDesc(String longDesc) {
		this.lastUpdate = Instant.now();
		this.longDesc = longDesc;
	}

	public void setShortDesc(String shortDesc) {
		this.lastUpdate = Instant.now();
		this.shortDesc = shortDesc;
	}

	public void setTitle(String title) {
		this.lastUpdate = Instant.now();
		this.title = title;
	}
}
