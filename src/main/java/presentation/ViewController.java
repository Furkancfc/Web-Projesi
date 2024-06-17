package presentation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.DispatcherServlet;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Account;
import model.Category;
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
	public String pageName;
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
			return true;
		} else {
			return false;
		}
	}

	// page configurations for Home app contents

	private String setPage(String page, HttpServletRequest req) {
		// req = /index/about --> css/index/about/index.css
		// * req = /login --> css/login/index.css, jsp/login/index.jsp,
		// js/login/login.js
		this.pageTitle = null;
		this.pageCss = "css" + page + "/index.css";
		this.pageJs = "js" + page + "/index.js";
		this.pageContent = "/jsp" + page + "/index.jsp";
		this.pageHead = "/jsp" + page + "/head.jsp";
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
	public void forward(String page, String to, HttpServletRequest req, HttpServletResponse resp) {
		try {
			setPage(page, req);
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

	public void redirect(String page, String to, HttpServletRequest req, HttpServletResponse resp) {
		try {
			setPage(page, req);
			resp.sendRedirect(to);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Controller
	class AdminController {
		@GetMapping(path = { "/admin/**", "/admin" })
		public void doGet(HttpServletRequest req, HttpServletResponse resp) {
			forward(req.getRequestURI(), "/jsp/admin/layout.jsp", req, resp);
		}

		@PostMapping(path = { "/admin/categoryManagement", "/admin/categoryManagement/" })
		public void postManageCategory(HttpServletRequest req, HttpServletResponse resp) {
			String name = (String) req.getAttribute("name");
			categoryService.createCategory(new Category(name));
		}

		@PostMapping({ "/admin/accountManagement", "/admin/accountManagement/" })
		public void postManageAccount() {

		}

		@PostMapping({ "/admin/itemManagement", "/admin/itemManagement/" })
		public void postManageItem() {
		}
	}

	@Controller
	class IndexController {
		@GetMapping(path = { "/" })
		public void getIndex(Model m, HttpServletRequest req, HttpServletResponse resp) {
			m.addAttribute("categories", categoryService.getCategories());
			forward("/index", "/jsp/template/layout.jsp", req, resp);
		}

		@GetMapping(path = { "/index**", "/index/**" })
		public void doGet(HttpServletRequest req, HttpServletResponse resp) {
			forward(req.getRequestURI(), "/jsp/template/layout.jsp", req, resp);
		}

		@RequestMapping(path = { "/logout", "/logout/" })
		public void logout(HttpSession session, HttpServletRequest req, HttpServletResponse resp) {
			session.removeAttribute("userId");
			forward("/index", "/jsp/template/layout.jsp", req, resp);
		}

		@Controller
		class LoginController {
			@GetMapping("/login")
			public void getLogin(HttpServletRequest req, HttpServletResponse resp, HttpSession session) {
				if (!checkSession(req, resp))
					forward("/login", "/jsp/template/layout.jsp", req, resp);
				else
					forward("/index", "/jsp/template/layout.jsp", req, resp);
			}

			@PostMapping("/login")
			public void postLogin(HttpServletRequest req, HttpServletResponse resp) {
				String email = req.getParameter("email");
				String password = req.getParameter("password");
				Account ac = MainDispatcher.getUser(email);
				if (ac != null && ac.getPassword().equals(password)) {
					req.getSession().setMaxInactiveInterval(60 * 60 * 24 * 7);
					new Session(req.getSession(), ac);
					forward("/index", "/jsp/template/layout.jsp", req, resp);
				} else {
					redirect("/login", "/jsp/template/layout.jsp", req, resp);
				}
			}
		}

		@Controller
		class RegisterController {
			@GetMapping("/register")
			public void getRegister(HttpServletRequest req, HttpServletResponse resp, HttpSession session) {
				if (!checkSession(req, resp)) {
					forward("signup", "/jsp/template/layout.jsp", req, resp);
				} else
					redirect("/index", "/jsp/template/layout.jsp", req, resp);
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
					new Session(req.getSession(), ac);
					forward("/index", "/jsp/template/layout.jsp", req, resp);
				} else {
					forward("/signup", "/jsp/template/layout.jsp", req, resp);
				}
			}
		}
	}
}