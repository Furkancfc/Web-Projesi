package webapp;

import jakarta.servlet.http.HttpSession;
import model.Account;

public class Session {
	private HttpSession session;
	public Session(HttpSession session, Account ac) {
		this.session = session;
		this.session.setAttribute("userId", ac.getUserId());
		this.session.setAttribute("id", session.getId());
		this.session.setAttribute("userEmail", ac.getEmail());
		this.session.setAttribute("auth", ac.getAuth());
	}
}
