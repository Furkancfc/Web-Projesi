package model;

import webapp.MainDispatcher;
import java.time.*;
import java.util.Base64;
import model.interfaces.IAccount;

public class Account implements IAccount {
	private static final long serialVersionUID = 1L;

	private String username;
	private String email;
	private String password;
	private Instant createTime;
	private String auth;

	private Cart userCart;
	private Orders orders;

	private String userId;

	public Account(String email, String password, String username, String auth) {
		this.email = email;
		this.password = Base64.getEncoder().encodeToString(password.getBytes());
		this.username = username;
		this.createTime = Instant.now();
		this.auth = auth;

		this.userId = MainDispatcher.createRandomId();

		this.userCart = new Cart(this.userId);
		this.orders = new Orders(this.userId);
	}

	public String getAuth() {
		return this.auth;
	}

	public void setAuth(String auth) {
		this.auth = auth;
	}

	public Instant getCreateTime() {
		return createTime;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return new String(Base64.getDecoder().decode(this.password.getBytes()));
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setUserName(String name) {
		this.username = name;
	}

	public String getUserName() {
		return username;
	}

	public void setCreateTime(Instant createTime) {
		this.createTime = createTime;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setCart(Cart userCart) {
		userCart.setCartId(this.userId);
		this.userCart = userCart;
	}

	public void setUserId(String userId) {
		this.userId = userId;
		this.getCart().setCartId(userId);
		this.getOrders().setOrdersId(userId);
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Cart getCart() {
		return userCart;
	}

	public String getUserId() {
		return userId;
	}

	public String getUsername() {
		return username;
	}

	public void setOrders(Orders orders) {
		orders.setOrdersId(this.userId);
		this.orders = orders;
	}

	public Orders getOrders() {
		return orders;
	}
}
