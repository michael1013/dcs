package cn.gnetop.dcs.portal.listener;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.util.StringUtils;

public class BeanUtils {
	private static Logger log = Logger.getLogger(BeanUtils.class);

	@SuppressWarnings("unchecked")
	public static <T> T getBean(Class<T> clazz, String name, ApplicationContext ctx) {
		if (null == ctx) {
			ctx = ContextHolder.getCtx();
		}

		if (StringUtils.isEmpty(name)) {
			if (null == clazz) {
				return null;
			}
			name = clazz.getSimpleName();
		}
		boolean find = ctx.containsBean(name);

		if (!find) {
			name = StringUtils.uncapitalize(name);
		}

		find = ctx.containsBean(name);
		if (!find) {
			name = StringUtils.capitalize(name);
		}

		find = ctx.containsBean(name);
		if (find) {
			return (T)ContextHolder.getCtx().getBean(name);
		}

		return null;
	}

	public static <T> T getBean(Class<T> clazz, String name) {
		return getBean(clazz, name, null);
	}

	public static <T> T getBean(Class<T> clazz) {
		return getBean(clazz, null);
	}

	public static Object getBean(String name) {
		return getBean(null, name);
	}

	public static <T> T instBean(Class<T> clazz, String className) {
		if (StringUtils.isEmpty(className)) {
			return null;
		}
		try {
			@SuppressWarnings("unchecked")
			Class<T> c = (Class<T>) Class.forName(className);
			return c.newInstance();
		} catch (Exception e) {
			log.error(className + " cannot instance to a class", e);
		}
		return null;
	}

}
