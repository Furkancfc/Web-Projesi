package webapp;

import model.*;
import service.implement.UserServiceImpl;

import java.io.IOException;
import java.net.URI;
import java.security.SecureRandom;
import java.time.Instant;
import java.time.ZoneOffset;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.annotation.AnnotationConfigurationException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.HttpServletBean;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import dao.*;
import dao.implement.UserDaoImpl;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class MainDispatcher extends DispatcherServlet {
	private static final long serialVersionUID = -2020821112498604792L;
	public static Map<String, Account> users;
	private static WebApplicationContext context;
	@Autowired
	public UserDaoImpl userDao;
	
	public MainDispatcher() {
		super();
		context = new XmlWebApplicationContext();
		setApplicationContext(context);
	}

	public static boolean putUser(Account ac) {
		return users.put(ac.getEmail(), ac) == null ? true : false;
	}

	public static Account getUser(String email) {
		return users.get(email);
	}

	public static String createRandomId() {
		SecureRandom scr = new SecureRandom();
		byte[] bytes = new byte[64];
		scr.nextBytes(bytes);
		return Base64.getEncoder().encodeToString(bytes);
	}

	@Override
	protected void doDispatch(HttpServletRequest request, HttpServletResponse response) throws Exception {
		super.doDispatch(request, response);
	}

	public static WebApplicationContext getContext() {
		return context;
	}

}
