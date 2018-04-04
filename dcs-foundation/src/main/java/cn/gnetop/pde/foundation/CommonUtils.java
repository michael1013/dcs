package cn.gnetop.pde.foundation;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;

import cn.gnetop.pde.foundation.config.GlobalConfig;
import cn.gnetop.pde.foundation.schema.KeyValue;

public class CommonUtils {
	public static String getUUID() {
		return UUID.randomUUID().toString();
	}

	public static String addServerUrl(String url) {
		String serverUrl = GlobalConfig.getConfig("base.server.url", "http://www.livow.net");
		String value = StringUtils.nvl(url);
		if (StringUtils.isNotBlank(url) && !url.startsWith("http://") && !url.startsWith("https://")) {
			value = serverUrl + url;
		}
		return value;
	}

	public static String getServerUrl(HttpServletRequest request) {
		return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
		        + request.getContextPath();
	}

	public static String subFilePath(String str) {
		if (StringUtils.isNotBlank(str)) {
			if (str.startsWith("file://")) {
				str = str.substring(6);
			} else if (str.startsWith("file:/")) {
				str = str.substring(5);
			}
			if (str.endsWith("/") || str.endsWith("\\")) {
				str = str.substring(0, str.length() - 1);
			}
		}
		return str;
	}

	public static int getIntParam(HttpServletRequest req, String key, int def) {
		int value;
		if (null != req) {
			String v = req.getParameter(key);
			if (StringUtils.isNotBlank(v)) {
				value = NumberUtils.toInt(v);
			} else {
				value = def;
			}
		} else {
			value = def;
		}
		return value;
	}

	public static String urlEncode(String data) {
		try {
			data = URLEncoder.encode(data, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return data;
	}

	public static String urlDecode(String data) {
		try {
			data = URLDecoder.decode(data, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return data;
	}

	public static String getExtraValue(List<KeyValue> extraInfo, String key) {
		if (StringUtils.isNotBlank(key) && CollectionUtils.isNotEmpty(extraInfo)) {
			for (KeyValue keyValue : extraInfo) {
				if (key.equals(keyValue.getKey())) {
					return String.valueOf(keyValue.getValue());
				}
			}
		}
		return null;
	}
}
