package cn.gnetop.pde.foundation.config;

import java.io.IOException;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang3.StringUtils;

import cn.gnetop.pde.foundation.MapUtils;

public class GlobalConfig {

	@SuppressWarnings("unchecked")
	private static final Map<String, String> configMap = MapUtils.newHashMap();

	static {
		Properties prop = new Properties();
		try {
			prop.load(GlobalConfig.class.getResourceAsStream("/config.properties"));
			for (Map.Entry<Object, Object> entry : prop.entrySet()) {
				configMap.put(String.valueOf(entry.getKey()), String.valueOf(entry.getValue()));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		for (String key : configMap.keySet()) {
			System.out.println(key + ":" + configMap.get(key));
		}
	}

	public static String getConfig(String key, String def) {
		String value = configMap.get(key);
		if (StringUtils.isEmpty(value)) {
			value = def;
		}
		return value;
	}

	public static int getIntConfig(String key, int def) {
		String valueStr = configMap.get(key);
		int value = def;
		if (StringUtils.isNotEmpty(valueStr)) {
			value = NumberUtils.toInt(valueStr, def);
		}
		return value;
	}

	public static Map<String, String> getConfigmap() {
		return configMap;
	}

}
