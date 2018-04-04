package cn.gnetop.pde.foundation;

import java.util.Map;

import cn.gnetop.pde.foundation.config.GlobalConfig;
import cn.gnetop.pde.foundation.encryte.DESUtils;

public final class PdeUtils {

	private static final String HEADER_ENCODE_KEY = GlobalConfig.getConfig("header.encode.key", "gNetopSZ");

	private PdeUtils() {

	}

	public static String sendPost(String url, String json, String plat) {
		return HttpUtils.sendHttpRequest(url, "POST", plat, getHeader(), json);
	}

	public static Map<String, String> getHeader() {
		Map<String, String> header = MapUtils.newHashMap();
		header.put("authenticateCode", getAuthenticateCode());
		return header;
	}

	public static String getAuthenticateCode() {
		String date = DateUtils.getDateString("yyyyMMddHHmmss");
		String des = DESUtils.encode(HEADER_ENCODE_KEY, "pde|gnetop|" + date);
		return des;
	}
}
