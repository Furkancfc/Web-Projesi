package webapp;

import model.*;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Map;

import org.apache.catalina.util.URLEncoder;
import org.springframework.web.context.*;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@WebServlet
@MultipartConfig
public class MainDispatcher extends DispatcherServlet {

	private static final long serialVersionUID = -2020821112498604792L;
	private static WebApplicationContext context;

	public MainDispatcher() {
		super();
		context = new XmlWebApplicationContext();
		setApplicationContext(context);
	}

	public static String createRandomId() {
		SecureRandom scr = new SecureRandom();
		byte[] bytes = new byte[64];
		scr.nextBytes(bytes);
		return java.net.URLEncoder.encode(Base64.getEncoder().encodeToString(bytes));
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

		String savePath = System.getProperty("wtp.deploy") + context.getServletContext().getContextPath() + "/images";
		String filePath = savePath + File.separator + part.getSubmittedFileName() + "_" + fileName;
		File fileSaveDir = new File(filePath);
		String absolutePath = fileSaveDir.getAbsolutePath(); // server tarafta file kaydetmek icin kullanilir
		String serverRelativePath = context.getServletContext().getContextPath() + "/images/" + fileSaveDir.getName(); // bu
																														// deger
																														// objec
																														// icinde
																														// kaydedilmek
																														// ve
																														// client
																														// tarafinda
																														// requestlerde
																														// kullanmak
																														// icin
																														// dondurulur
		if (fileSaveDir != null && !fileSaveDir.exists()) {
			fileSaveDir.mkdirs();
		}
		part.write(absolutePath);
		return serverRelativePath; // Return URL or path where the file is saved
	}
}
