package model;

import webapp.MainDispatcher;
import jakarta.persistence.*;
import java.time.*;
import java.util.Base64;
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
	private String userId;
	private Instant createTime;
	private Cart userCart;
	private String auth;

	public Account(String email, String password, String username, String auth) {
		lock = new ReentrantLock();
		this.userId = MainDispatcher.createRandomId();
		this.email = email;
		this.password = Base64.getEncoder().encodeToString(password.getBytes());
		this.username = username;
		this.createTime = Instant.now();
		this.userCart = new Cart();
		this.auth = auth;
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

		return new String(Base64.getDecoder().decode(this.password.getBytes()));
	}

	public void setPassword(String password) {
		try {
			lock.lock();
			this.password = password;
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

	public Cart getUserCart() {
		return userCart;
	}

	public String getUserId() {
		return userId;
	}
}
