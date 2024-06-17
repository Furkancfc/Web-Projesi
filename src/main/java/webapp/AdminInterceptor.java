package webapp;

import java.time.Instant;
import java.time.ZoneOffset;

import org.springframework.*;
import org.springframework.web.*;
import org.springframework.web.servlet.*;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class AdminInterceptor implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		if (session != null && session.getAttribute("auth") != null && session.getAttribute("auth").equals("admin"))
			return HandlerInterceptor.super.preHandle(request, response, handler);
		else {
			request.getRequestDispatcher(DispatcherServlet.SERVLET_CONTEXT_PREFIX + "error/404.jsp").forward(request, response);
			return false;
		}
	}
}