package model;

import webapp.MainDispatcher;
import jakarta.persistence.*;
import java.time.*;
import java.util.Base64;
import java.util.*;

import org.apache.commons.lang3.StringUtils;
import org.springframework.lang.NonNull;

import model.interfaces.IAccount;

import java.util.concurrent.*;
import java.util.concurrent.locks.*;

public class Account implements IAccount {
	private ReentrantLock lock;
	private static final long serialVersionUID = 1L;

	private String username;
	private String email;
	private String password;
	private Instant createTime;
	private String auth;

	private Cart userCart;
	private Orders orders;

	private String userId;
	private String cartId;
	private String ordersId;

	public Account(String email, String password, String username, String auth) {
		lock = new ReentrantLock();
		this.email = email;
		this.password = Base64.getEncoder().encodeToString(password.getBytes());
		this.username = username;
		this.createTime = Instant.now();
		this.auth = auth;

		this.userId = MainDispatcher.createRandomId();

		this.userCart = new Cart(this.userId);
		this.orders = new Orders(this.userId);

		this.cartId = this.userCart.getCartId();
		this.ordersId = this.orders.getOrdersId();

	}

	public String getAuth() {
		return this.auth;
	}

	public void setAuth(String auth) {
		this.auth = auth;
	}

	public Instant getCreateTime() {
		while (lock.isLocked())
			;
		return createTime;
	}

	public String getEmail() {
		while (lock.isLocked())
			;
		return email;
	}

	public String getPassword() {
		while (lock.isLocked())
			;

		return new String(Base64.getDecoder().decode(this.password));
	}

	public void setPassword(String password) {
		try {
			lock.lock();
			this.password = Base64.getEncoder().encodeToString(password.getBytes());
		} finally {
			lock.unlock();
		}
	}

	public void setUserName(String name) {
		try {
			lock.lock();
			this.username = name;
		} finally {
			lock.unlock();
		}
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

	public void setLock(ReentrantLock lock) {
		this.lock = lock;
	}

	public void setUserCart(Cart userCart) {
		this.userCart = userCart;
		this.cartId = userCart.getCartId();
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Cart getUserCart() {
		return userCart;
	}

	public String getUserId() {
		return userId;
	}

	public Orders getUserOrders() {
		return orders;
	}

	public String getUsername() {
		return username;
	}

	public void setUserOrders(Orders orders) {
		this.orders = orders;
		this.ordersId = orders.getOrdersId();
	}

	public String getCartId() {
		return cartId;
	}

	public Orders getOrders() {
		return orders;
	}

	public String getOrdersId() {
		return ordersId;
	}

	public void setCartId(String cartId) {
		this.cartId = cartId;
	}

	public void setOrders(Orders orders) {
		this.orders = orders;
	}

	public void setOrdersId(String ordersId) {
		this.ordersId = ordersId;
	}
}
