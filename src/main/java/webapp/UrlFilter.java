package webapp;

import java.io.IOException;

import org.springframework.stereotype.Component;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jdk.internal.org.jline.utils.Log;

@Component
public class UrlFilter implements Filter {
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		Log.info("Request before Filter : " + request.getRemoteAddr());
		chain.doFilter(request, response);
		Log.info("Request after Filter : " + request.getRemoteAddr());
	}

}
