package cn.gnetop.pde.foundation.log;

import org.apache.log4j.Logger;

public class InterfaceThirdLog {
	private static final Logger logger = Logger.getLogger("interfaceThirdLog");

	public static void log(String uuid, String url, String method, String plat, String type, Object o) {
		StringBuffer sb = new StringBuffer();
		sb.append(uuid).append('|');
		sb.append(url).append('|');
		sb.append(method).append('|');
		sb.append(plat).append('|');
		sb.append(type).append('|');
		sb.append(String.valueOf(o).replaceAll("\\s", ""));
		logger.info(sb.toString());
	}
}
