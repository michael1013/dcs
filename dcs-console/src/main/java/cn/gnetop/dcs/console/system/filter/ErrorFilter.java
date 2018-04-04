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

import org.apache.log4j.Logger;

import cn.gnetop.pde.foundation.log.LogManager;

public class ErrorFilter implements Filter {

	private static final Logger logger = LogManager.getConsoleLog();

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse rsp = (HttpServletResponse) response;
		try {
			chain.doFilter(req, rsp);
		} catch (Throwable e) {
			logger.error("", e);
			req.getRequestDispatcher("/console/WEB-INF/view/error.jsp").forward(req, rsp);
		}
	}

	@Override
	public void destroy() {

	}

}
