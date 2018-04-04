package cn.gnetop.dcs.system.helper;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;

import cn.gnetop.pde.foundation.MapUtils;

public class ConfigManager {

	@SuppressWarnings("unchecked")
	private static Map<String, String> configMap = MapUtils.newHashMap();

	@SuppressWarnings("unchecked")
	private static Map<String, String> configOpenMap = MapUtils.newHashMap();

	public static void initMap(Map<String, String> configMap, Map<String, String> configOpenMap) {
		ConfigManager.configMap = configMap;
		ConfigManager.configOpenMap = configOpenMap;
	}

	public static int getIntConfig(String key, int def) {
		String value = configMap.get(key);
		int intValue = def;
		if (StringUtils.isNotBlank(value)) {
			intValue = NumberUtils.toInt(value);
		}
		return intValue;
	}

	public static long getLongConfig(String key, long def) {
		String value = configMap.get(key);
		long intValue = def;
		if (StringUtils.isNotBlank(value)) {
			intValue = NumberUtils.toLong(value);
		}
		return intValue;
	}

	public static int getLongConfig(String key, int def) {
		String value = configMap.get(key);
		int intValue = def;
		if (StringUtils.isNotBlank(value)) {
			intValue = NumberUtils.toInt(value);
		}
		return intValue;
	}

	public static String getConfig(String key, String def) {
		String value = configMap.get(key);
		if (StringUtils.isBlank(value)) {
			value = def;
		}
		return value;
	}

	public static String getOpenConfig(String key, String def) {
		String value = configOpenMap.get(key);
		if (StringUtils.isBlank(value)) {
			value = def;
		}
		return value;
	}
}
