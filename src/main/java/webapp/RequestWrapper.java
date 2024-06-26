package webapp;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.stream.Collectors;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;

public class RequestWrapper extends HttpServletRequestWrapper implements HttpServletRequest {
	private String customURL;

	public RequestWrapper(HttpServletRequest request) {
		super(request);
		try {
			this.customURL = normalizeURL(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public StringBuffer getRequestURL() {
		return new StringBuffer(customURL);
	}

	@Override
	public String getRequestURI() {
		try {
			URI uri = new URI(getRequestURL().toString());
			return uri.getPath();
		} catch (URISyntaxException e) {
			e.printStackTrace();
			return super.getRequestURI();
		}
	}

	public String normalizeURL(HttpServletRequest req) throws URISyntaxException {
		URI uri = new URI(req.getRequestURL().toString());
		// Normalize scheme and host to lowercase
		String scheme = uri.getScheme().toLowerCase();
		String host = uri.getHost() != null ? uri.getHost().toLowerCase() : "";
		// Normalize path
		String path = uri.getPath();
		path = URLDecoder.decode(path, StandardCharsets.UTF_8);
		path = path.replaceAll("/+", "/");
		if (path.length() > 1 && path.endsWith("/")) {
			path = path.substring(0, path.length() - 1);
		}

		// Normalize query
		String query = uri.getQuery();
		if (query != null) {
			query = Arrays.stream(query.split("&")).sorted().collect(Collectors.joining("&"));
		}

		// Normalize fragment
		String fragment = uri.getFragment();
		if (fragment != null) {
			fragment = URLDecoder.decode(fragment, StandardCharsets.UTF_8);
		}

		// Reconstruct the normalized URL
		URI normalizedUri = new URI(scheme, null, host, req.getServerPort(), path, query, fragment);

		return normalizedUri.toString();
	}
}
