package webapp;

import java.time.*;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import jakarta.servlet.http.HttpSession;
import model.Account;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

public class Session {
	private HttpSession session;
	private Account ac;

	public Session(HttpSession session, Account ac) {
		this.ac = ac;
		this.session = session;
		this.session.setAttribute("userId", getUserId());
		this.session.setAttribute("id", session.getId());
		this.session.setAttribute("userEmail", ac.getEmail());
		this.session.setAttribute("auth", ac.getAuth());
	}

	public HttpSession getSession() {
		return this.session;
	}

	public String getUserId() {
		return ac.getUserId();
	}
}
