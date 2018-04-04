package cn.gnetop.pde.foundation.log;

import org.apache.log4j.Logger;

public class InterfaceLog {
	private static final Logger logger = Logger.getLogger("interfaceLog");

	public static void log(String uuid, String auth, String ip, String method, String service, String action,
	        String type, Object o) {
		StringBuffer sb = new StringBuffer();
		sb.append(uuid).append('|');
		sb.append(auth).append('|');
		sb.append(ip).append('|');
		sb.append(method).append('|');
		sb.append(service).append('|');
		sb.append(action).append('|');
		sb.append(type).append('|');
		sb.append(String.valueOf(o).replaceAll("\\s", ""));
		logger.info(sb.toString());
	}
}
