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

import cn.gnetop.dcs.console.util.UserSession;

public class AccessFilter implements Filter {

	private static final String[] publicUris = { "/console/login", "/console/WEB-INF", "/console/static", "/console/vc",
			"/console/logout", "/console/p" };

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse rsp = (HttpServletResponse) response;
		if (req.getRequestURI().equals("/console/")) {
			rsp.sendRedirect("/console/index");
			return;
		}
		if (this.isPublicUri(req) || UserSession.isLogin()) {
			chain.doFilter(request, response);
		} else {
			rsp.sendRedirect(req.getContextPath() + "/login");
		}
	}

	private boolean isPublicUri(HttpServletRequest req) {
		String uri = req.getRequestURI();
		for (String s : publicUris) {
			if (uri.startsWith(s)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void destroy() {
	}

}
