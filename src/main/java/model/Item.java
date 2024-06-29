package model;

import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.time.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collection;
import java.util.List;

import org.springframework.util.Base64Utils;

import io.micrometer.common.lang.NonNull;
import jakarta.servlet.http.Part;
import jakarta.persistence.*;

import model.interfaces.IItem;
import webapp.MainDispatcher;

public class Item implements IItem {
	private static final long serialVersionUID = 1L;
	private String itemId;
	private String title;
	private String shortDesc;
	private String longDesc;
	private List<String> imagespaths;
	private Instant createTime; // create time
	private Instant lastUpdate; // write access
	private Instant lastAccess; // read access
	private String categoryName;
	private Double price;

	public Item(@NonNull String title, @NonNull String shortDesc, @NonNull String longDesc,
			@NonNull String categoryName, Collection<Part> images, @NonNull Double price) {
		this.itemId = MainDispatcher.createRandomId();
		this.title = title;
		this.shortDesc = shortDesc;
		this.longDesc = longDesc;
		this.createTime = Instant.now();
		this.lastUpdate = createTime;
		this.lastAccess = lastUpdate;
		this.categoryName = categoryName;
		this.price = price;
		this.imagespaths = new ArrayList<String>();
		ArrayList<Part> p;
		if ((p = new ArrayList<Part>()).addAll(images)) {
			images = p;
		}
		for (Part c : images) {
			if (c.getName().equals("image") && c.getSize() > 0) {
				try {
					String filename = createTime.toString() + ".jpeg";
					String imageurl = MainDispatcher.saveFile(c, filename);
					addImage(imageurl);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

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

	public String getCategoryName() {
		return categoryName;
	}

	public List<String> getImageURLs() {
		return imagespaths;
	}

	public Double getPrice() {
		return price;
	}

	public void setImageURI(List<String> images) {
		this.lastUpdate = Instant.now();
		this.imagespaths = images;
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

	public void setCreateTime(Instant createTime) {
		this.createTime = createTime;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public void setLastAccess(Instant lastAccess) {
		this.lastAccess = lastAccess;
	}

	public void setLastUpdate(Instant lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public void addImage(String imageURL) {
		if (imageURL != null) {
			this.imagespaths.add(imageURL);
		}
	}

	public String getURL() {
		return URLEncoder.encode(getItemId());
	}
}
