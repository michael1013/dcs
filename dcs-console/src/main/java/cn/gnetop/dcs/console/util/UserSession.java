package cn.gnetop.dcs.console.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.gnetop.dcs.console.system.filter.EnvironmentFilter;
import cn.gnetop.dcs.dao.schema.AdminUser;

public class UserSession {

	private static final String USERSESSION_KEY = "usersession_key";

	public static HttpServletRequest getRequest() {
		return EnvironmentFilter.local.get().getRequest();
	}

	public static HttpServletResponse getResponse() {
		return EnvironmentFilter.local.get().getResponse();
	}

	public static HttpSession getSession() {
		return getRequest().getSession();
	}

	public static void setSessionAttr(String name, Object value) {
		getSession().setAttribute(name, value);
	}

	public static Object getSessionAttr(String name) {
		return getSession().getAttribute(name);
	}

	public static void setUserSession(AdminUser user) {
		setSessionAttr(USERSESSION_KEY, user);
	}

	public static AdminUser getLoginUser() {
		Object o = getSessionAttr(USERSESSION_KEY);
		if (null != o) {
			return (AdminUser) o;
		}
		return null;
	}

	public static void logout() {
		getSession().removeAttribute(USERSESSION_KEY);
	}

	public static boolean isLogin() {
		return null != getLoginUser();
	}
}
