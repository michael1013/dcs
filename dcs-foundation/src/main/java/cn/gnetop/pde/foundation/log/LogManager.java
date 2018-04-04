package cn.gnetop.pde.foundation.log;

import org.apache.log4j.Logger;

public class LogManager {

	public static Logger getLogger() {
		return Logger.getLogger("debugLog");
	}
	
	public static Logger getConsoleLog(){
		return Logger.getLogger("consoleLog");
	}
}
