package presentation;

import java.time.Instant;
import java.time.ZoneOffset;
import java.util.Iterator;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.View;

import com.google.protobuf.Service;

import dao.implement.CartDaoImpl;
import dao.implement.CategoryDaoImpl;
import dao.implement.ItemDaoImpl;
import dao.implement.UserDaoImpl;
import dao.interfaces.UserDao;
import io.micrometer.observation.Observation.Context;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Account;
import service.implement.CartServiceImpl;
import service.implement.CategoryServiceImpl;
import service.implement.ItemServiceImpl;
import service.implement.UserServiceImpl;
import webapp.*;

@Controller
public class ViewController {
	@Autowired
	private service.implement.CartServiceImpl cartService;
	@Autowired
	private service.implement.UserServiceImpl userService;
	@Autowired
	private service.implement.CategoryServiceImpl categoryService;
	@Autowired
	private service.implement.ItemServiceImpl itemService;
	public String page;
	public String app;
	public String pageTitle;
	private String pageCss;
	private String pageJs;
	private String pageContent;
	private String pageHead;

	public ViewController() {
		super();
	}

	/**
	 * 
	 * @param resp
	 * @param session
	 * @return boolean and forwards to index. Do not redirect after this if
	 *         successfully
	 * 
	 */
	public boolean checkSession(HttpServletRequest req, HttpServletResponse resp) {
		HttpSession session = req.getSession();
		if (session != null && session.getAttribute("userId") != null) {
			forward("index", req, resp);
			return true;
		} else {
			return false;
		}
	}

	// page configurations for Home app contents
	private String setPage(HttpServletRequest req, String page) {
		this.pageTitle = null;
		this.app = "main";
		this.page = "/jsp/" + app + "/" + page;
		this.pageHead = this.page + "/" + "head.jsp";
		this.pageCss = "css/" + app + "/" + page + "/" + page + ".css";
		this.pageJs = "js/" + app + "/" + page + "/" + page + ".js";
		this.pageContent = this.page + "/" + page + ".jsp";
		req.setAttribute("page", page);
		req.setAttribute("pageContent", pageContent);
		req.setAttribute("pageCss", pageCss);
		req.setAttribute("pageJs", pageJs);
		req.setAttribute("pageHead", pageHead);
		return pageContent;
	}

	private String setPage(HttpServletRequest req, String app, String page) {
		this.pageTitle = null;
		this.app = app;
		this.page = "/jsp/" + app;
		this.pageHead = this.page + "/" + page + "/" + "head.jsp";
		this.pageCss = "css/" + app + "/" + page + "/" + page + ".css";
		this.pageJs = "js/" + app + "/" + page + "/" + page + ".js";
		this.pageContent = this.page + "/" + page + "/" + page + ".jsp";
		req.setAttribute("page", page);
		req.setAttribute("pageContent", pageContent);
		req.setAttribute("pageCss", pageCss);
		req.setAttribute("pageJs", pageJs);
		req.setAttribute("pageHead", pageHead);
		return pageContent;
	}

	/**
	 * 
	 * @param page
	 * @param req
	 * @param resp Forwards request to layout.jsp
	 */
	public void forward(String page, HttpServletRequest req, HttpServletResponse resp) {
		try {
			setPage(req, page);
			req.getRequestDispatcher("/jsp/main/template/layout.jsp").forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void forward(String page, String to, HttpServletRequest req, HttpServletResponse resp) {
		try {
			setPage(req, page);
			req.getRequestDispatcher(to).forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void forward(String app, String page, String to, HttpServletRequest req, HttpServletResponse resp) {
		try {
			setPage(req, app, page);
			req.getRequestDispatcher(to).forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param page
	 * @param req
	 * @param resp Redirects to layout.jsp
	 */
	public void redirect(String page, HttpServletRequest req, HttpServletResponse resp) {
		try {
			setPage(req, page);
			resp.sendRedirect("/project2/index");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void redirect(String page, String to, HttpServletRequest req, HttpServletResponse resp) {
		try {
			setPage(req, page);
			resp.sendRedirect(to);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@GetMapping(path = { "/", "/index" })
	public void getIndex(Model m, HttpServletRequest req, HttpServletResponse resp) {
		m.addAttribute("categories", categoryService.getCategories());
		forward("index", req, resp);
	}

	@Controller
	class AdminController {
		@GetMapping(path = { "/admin", "/admin/", "/admin/index" })
		public void getIndex(HttpServletRequest req, HttpServletResponse resp, HttpSession session) {
			if (session != null && session.getAttribute("auth") != null
					&& session.getAttribute("auth").equals("admin")) {
				forward("admin", "index", "/jsp/admin/layout.jsp", req, resp);
			} else {
				redirect("index", req, resp);
			}
		}

	}

	@Controller
	class LoginController {
		@GetMapping("/login")
		public void getLogin(HttpServletRequest req, HttpServletResponse resp, HttpSession session) {
			if (!checkSession(req, resp))
				forward("login", req, resp);
		}

		@PostMapping("/login")
		public void postLogin(HttpServletRequest req, HttpServletResponse resp) {
			String email = req.getParameter("email");
			String password = req.getParameter("password");
			Account ac = MainDispatcher.getUser(email);
			if (ac != null && ac.getPassword().equals(password)) {
				req.getSession().setMaxInactiveInterval(60 * 60 * 24 * 7);
				Session s = new Session(req.getSession(), ac);
				forward("index", req, resp);
			} else {
				forward("login", req, resp);
			}
		}
	}

	@Controller
	class RegisterController {
		@GetMapping("/register")
		public void getRegister(HttpServletRequest req, HttpServletResponse resp, HttpSession session) {
			if (!checkSession(req, resp)) {
				forward("signup", req, resp);
			}
		}

		@PostMapping("/register")
		public void postRegister(HttpServletRequest req, HttpServletResponse resp) {
			String username = req.getParameter("username");
			String email = req.getParameter("email");
			String password = req.getParameter("password");
			String passagain = req.getParameter("confirm-password");
			if (password.equals(passagain)) {
				Account ac = new Account(email, password, username, null);
				MainDispatcher.putUser(ac);
				req.getSession().setMaxInactiveInterval(60 * 60 * 24 * 7);
				Session s = new Session(req.getSession(), ac);
				forward("index", req, resp);
			} else {
				forward("signup", req, resp);
			}
		}
	}
}