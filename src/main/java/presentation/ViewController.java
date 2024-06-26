package presentation;

import java.awt.PageAttributes.MediaType;
import java.awt.datatransfer.MimeTypeParseException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity.HeadersBuilder;
import org.springframework.http.client.support.HttpRequestWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MimeType;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.i18n.AcceptHeaderLocaleContextResolver;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.function.ServerRequest.Headers;

import io.micrometer.common.lang.NonNull;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import model.Account;
import model.Category;
import model.Item;
import webapp.*;

@MultipartConfig
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
	@Autowired
	private service.implement.OrdersServiceImpl ordersService;

	private RequestWrapper rw;
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
	public boolean checkSession(RequestWrapper req, HttpServletResponse resp) {
		HttpSession session = req.getSession();
		if (session != null && session.getAttribute("userId") != null) {
			return true;
		} else {
			return false;
		}
	}

	// page configurations for Home app contents

	private void setPage(String page, RequestWrapper req) {

		page = page.replace(req.getContextPath(), "");
		System.out.println(page);
		if (!req.getRequestURI().contains(".css") && !req.getRequestURI().contains(".js")
				&& !req.getRequestURI().equals(".jsp")) {
			this.pageCss = req.getContextPath() + "/css" + page + "/index.css";
			this.pageJs = req.getContextPath() + "/js" + page + "/index.js";
			this.pageContent = "/jsp" + page + "/index.jsp";
			this.pageHead = "/jsp" + page + "/head.jsp";
			System.out.println(pageContent);
			req.setAttribute("page", page);
			req.setAttribute("pageCss", pageCss);
			req.setAttribute("pageJs", pageJs);
			req.setAttribute("pageHead", pageHead);
		}
		req.setAttribute("pageContent", pageContent);
	}

	/**
	 * 
	 * @param page
	 * @param req
	 * @param resp Forwards request to layout.jsp
	 */
	public void forward(String page, String to, RequestWrapper req, HttpServletResponse resp) {
		try {
			setPage(page, req);
			req.getRequestDispatcher(to).forward(req, resp);
		} catch (

		Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param page
	 * @param req
	 * @param resp Redirects to layout.jsp
	 */
	public void redirect(String to, RequestWrapper req, HttpServletResponse resp) {
		try {
			to = req.getContextPath() + to;
			resp.sendRedirect(to);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@MultipartConfig
	@Controller
	class AdminController {
		static final String name = "/AdminPage";
		static final String layout = "/jsp/AdminPage/layout.jsp";

		@GetMapping(path = { name, name + "/**" })
		public void doGet(RequestWrapper req, HttpServletResponse resp) {
			if (req.getRequestURI().equals(req.getContextPath() + name)) {
				forward("/AdminPage/Dashboard", layout, req, resp);
				return;
			}
			forward(req.getRequestURI(), layout, req, resp);
			return;
		}

		@PostMapping(path = { name + "/Categories" })
		public void postManageCategory(RequestWrapper req, HttpServletResponse resp) {
			String name = (String) req.getParameter("name");
			if (name != null) {
				categoryService.addCategory(new Category(name));
			}
			forward("/AdminPage/Categories", layout, req, resp);
		}

		@PostMapping(path = { name + "/Products" })
		public void postProduct(RequestWrapper req, HttpServletResponse resp) {
			String title = req.getParameter("title");
			String shortDesc = req.getParameter("short-desc");
			String longDesc = req.getParameter("long-desc");
			String categoryName = req.getParameter("category");
			String price = req.getParameter("price");
			Collection<Part> parts;
			try {
				parts = req.getParts();
			} catch (Exception e) {
				e.printStackTrace();
				parts = null;

			}
			itemService.addItem(new Item(title, shortDesc, longDesc, categoryName, parts, price));
			forward(name + "/Products", layout, req, resp);
		}

		@PostMapping(path = { name + "/Users" })
		public void postUsers(RequestWrapper req, HttpServletResponse resp) {
			String username = req.getParameter("user-name");
			String email = req.getParameter("user-email");
			String password = req.getParameter("user-password");
			String auth = req.getParameter("auth");
			userService.createUser(new Account(email, password, username, auth));
		}
	}

	@Controller
	class IndexController {
		@GetMapping(path = { "/", "/CustomerPage/**" })
		public void getIndexPage(RequestWrapper req, HttpServletResponse resp) {
			if (req.getRequestURI().equals(req.getContextPath())
					|| req.getRequestURI().equals(req.getContextPath() + "/CustomerPage")) {
				forward("/CustomerPage/Index", "/jsp/CustomerPage/layout.jsp", req, resp);
				return;
			}
			forward(req.getRequestURI(), "/jsp/CustomerPage/layout.jsp", req, resp);
			return;
		}

		@RequestMapping(path = { "/logout", "/logout/" })
		public void logout(HttpSession session, RequestWrapper req, HttpServletResponse resp) {
			session.invalidate();
			redirect("/index", req, resp);
		}

		@Controller
		class LoginController {
			@GetMapping("/login")
			public void getLogin(RequestWrapper req, HttpServletResponse resp, HttpSession session) {
				if (!checkSession(req, resp))
					forward("/login", "/jsp/template/layout.jsp", req, resp);
				else
					redirect("/index", req, resp);
			}

			@PostMapping("/login")
			public void postLogin(RequestWrapper req, HttpServletResponse resp) {
				String email = req.getParameter("email");
				String password = req.getParameter("password");
				Account ac = MainDispatcher.getUser(email);
				if (ac != null && ac.getPassword().equals(password)) {
					req.getSession().setMaxInactiveInterval(60 * 60 * 24 * 7);
					new Session(req.getSession(), ac);
					redirect("/index", req, resp);
				} else {
					forward("/login", "/jsp/template/layout.jsp", req, resp);
				}
			}
		}

		@Controller
		class RegisterController {
			@GetMapping("/register")
			public void getRegister(RequestWrapper req, HttpServletResponse resp, HttpSession session) {
				if (!checkSession(req, resp)) {
					forward("/signup", "/jsp/template/layout.jsp", req, resp);
				} else {
					redirect("/index", req, resp);
				}
			}

			@PostMapping("/register")
			public void postRegister(RequestWrapper req, HttpServletResponse resp) {
				String username = req.getParameter("username");
				String email = req.getParameter("email");
				String password = req.getParameter("password");
				String passagain = req.getParameter("confirm-password");
				if (password.equals(passagain)) {
					Account ac = new Account(email, password, username, null);
					MainDispatcher.putUser(ac);
					req.getSession().setMaxInactiveInterval(60 * 60 * 24 * 7);
					new Session(req.getSession(), ac);
					redirect("/index", req, resp);
				} else {
					forward("/signup", "/jsp/template/layout.jsp", req, resp);
				}
			}
		}
	}

	public class AdminInterceptor implements HandlerInterceptor {
		@Override
		public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
				throws Exception {
			HttpSession session = request.getSession();
			if (session != null && session.getAttribute("auth") != null && session.getAttribute("auth").equals("admin"))
				return HandlerInterceptor.super.preHandle(request, response, handler);
			else {
				request.getRequestDispatcher(request.getContextPath() + "error/404.jsp").forward(request, response);
				return false;
			}
		}
	}

	public class MainInterceptor implements HandlerInterceptor {
		@Override
		public boolean preHandle(@NonNull HttpServletRequest req, @NonNull HttpServletResponse resp, Object handler)
				throws Exception {
			RequestWrapper rw = new RequestWrapper(req);
			System.out.printf(
					"===REQUEST===\nTIMESTAMP : %s\nCONTEXT PATH : %s\nMETHOD : %s\nREQUEST URI : %s\nREQUEST URL : %s\nREQUEST CONTENT TYPE:%s\n",
					Instant.now().atOffset(ZoneOffset.UTC), rw.getContextPath(), rw.getMethod(), rw.getRequestURI(),
					rw.getRequestURL(), rw.getContentType());
			req.getServletContext().setAttribute("categoryService", categoryService);
			req.getServletContext().setAttribute("userService", userService);
			req.getServletContext().setAttribute("cartService", cartService);
			req.getServletContext().setAttribute("itemService", itemService);
			req.getServletContext().setAttribute("ordersService", ordersService);
			return HandlerInterceptor.super.preHandle(rw, resp, handler);
		}

	}

}