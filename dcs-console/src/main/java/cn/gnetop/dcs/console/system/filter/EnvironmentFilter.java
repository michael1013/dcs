package cn.gnetop.dcs.console.system.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EnvironmentFilter implements Filter {

	public static final ThreadLocal<Environment> local = new ThreadLocal<Environment>();

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		local.remove();
		local.set(new Environment((HttpServletRequest) request, (HttpServletResponse) response));
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {

	}

	public class Environment {
		private HttpServletRequest request;
		private HttpServletResponse response;

		public Environment(HttpServletRequest request, HttpServletResponse response) {
			super();
			this.request = request;
			this.response = response;
		}

		public HttpServletRequest getRequest() {
			return request;
		}

		public void setRequest(HttpServletRequest request) {
			this.request = request;
		}

		public HttpServletResponse getResponse() {
			return response;
		}

		public void setResponse(HttpServletResponse response) {
			this.response = response;
		}
	}
}
