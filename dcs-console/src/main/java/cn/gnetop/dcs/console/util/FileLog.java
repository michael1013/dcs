package cn.gnetop.dcs.console.util;

import org.apache.log4j.Logger;

public class FileLog {

	private static Logger logger = Logger.getLogger("fileLog");

	public static void debug(Object message) {
		logger.debug(message);
	}

	public static void debug(Object message, Throwable t) {
		logger.debug(message, t);
	}

	public static void error(Object message) {
		logger.error(message);
	}

	public static void error(Object message, Throwable t) {
		logger.error(message, t);
	}
}
