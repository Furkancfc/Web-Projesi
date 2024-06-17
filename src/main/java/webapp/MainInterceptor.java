package webapp;

import java.time.Instant;
import java.time.ZoneOffset;

import org.springframework.*;
import org.springframework.web.*;
import org.springframework.web.servlet.*;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class MainInterceptor implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler) throws Exception {
		System.out.printf(
				"""
				===REQUEST===
				TIMESTAMP : %s
				METHOD : %s
				REQUEST URI : %s
				""", Instant.now().atOffset(ZoneOffset.UTC), req.getMethod(), req.getRequestURI());
		return HandlerInterceptor.super.preHandle(req, resp, handler);
	}

}
