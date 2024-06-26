package webapp;

import model.*;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.time.Instant;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

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

import dao.*;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@WebServlet
@MultipartConfig
public class MainDispatcher extends DispatcherServlet {

	private static final long serialVersionUID = -2020821112498604792L;
	public static Map<String, Account> users;
	private static WebApplicationContext context;

	public MainDispatcher() {
		super();
		context = new XmlWebApplicationContext();
		setApplicationContext(context);
		users = new HashMap<String, Account>();
		putUser(new Account("admin", "admin", "admin", "admin"));
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
		super.doDispatch(new RequestWrapper(request), response);
	}

	public static WebApplicationContext getContext() {
		return context;
	}

	public static final String getFileName(Part part) {
		String contentDisposition = part.getHeader("content-disposition");
		String[] tokens = contentDisposition.split(";");
		for (String token : tokens) {
			if (token.trim().startsWith("filename")) {
				return token.substring(token.indexOf('=') + 1).trim().replace("\"", "");
			}
		}
		return "unknown";
	}

	// Utility method to save file to desired location
	public static final String saveFile(Part part, String fileName) throws IOException {
		
		String savePath = System.getProperty("wtp.deploy")+context.getServletContext().getContextPath()+"/images";
		String filePath = savePath + File.separator + part.getSubmittedFileName() + "_" + fileName;
		File fileSaveDir = new File(filePath);
		String absolutePath = fileSaveDir.getAbsolutePath();
		String serverRelativePath= context.getServletContext().getContextPath() + "/images/" + fileSaveDir.getName() ;
		if (fileSaveDir != null && !fileSaveDir.exists()) {
			fileSaveDir.mkdirs();
		}
		part.write(absolutePath);
		return serverRelativePath; // Return URL or path where the file is saved
	}
}
