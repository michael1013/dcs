package cn.gnetop.dcs.system.helper;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import cn.gnetop.dcs.system.entity.MessageContext;
import cn.gnetop.dcs.system.entity.MessageHeader;
import cn.gnetop.dcs.system.entity.Request;
import cn.gnetop.pde.foundation.ArrayUtils;
import cn.gnetop.pde.foundation.JsonUtils;
import cn.gnetop.pde.foundation.MapUtils;
import cn.gnetop.pde.foundation.StringUtils;
import cn.gnetop.pde.foundation.log.LogManager;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public final class MessageContextHelper {

	private static final String CODE_UTF8 = "UTF-8";

	private static final String URI_JSON = "json";

	private static final String VERSION = "version";
	private static final String COMPONENTTYPE = "componentType";
	private static final String COMPONENTCODE = "componentCode";
	private static final String AUTHENTICATECODE = "authenticateCode";
	private static final String TIMESTAMP = "timeStamp";

	private static final Map<String, Class<?>> JSONALIASMAP = new HashMap<>();
	private static final Map<String, String> JSON_ALIAS = new HashMap<>();

	static {
	}

	public static String convertorServiceName(String uri) {
		int index = uri.indexOf(URI_JSON);
		return uri.substring(uri.indexOf('/', index) + 1, uri.lastIndexOf('/')) + "Service";
	}

	public static String convertorReqBody(HttpServletRequest request) {
		String json = null;
		// get方式的请求把参数列表转换成json
		if ("get".equalsIgnoreCase(request.getMethod())) {
			json = toGetMethodJson(request);
		} else {
			json = toPostMethodJson(request);
		}
		return json;

	}

	public static String toGetMethodJson(HttpServletRequest request) {
		
		LogManager.getLogger().debug(request.getQueryString());//TODO
		
		Map<String, String[]> paramMaps = request.getParameterMap();
		Map<String, Object> paramMap = MapUtils.newHashMap();
		for (Map.Entry<String, String[]> entry : paramMaps.entrySet()) {
			String key = entry.getKey();
			String[] values = entry.getValue();
			if (key.endsWith("[]")) {
				key = key.substring(0, key.lastIndexOf("["));
				paramMap.put(key, JSONArray.fromObject(values));
			} else if (values.length > 1) {
				paramMap.put(key, JSONArray.fromObject(values));
			} else if (ArrayUtils.isNotEmpty(values) && values.length == 1) {
				paramMap.put(key, values[0]);
			} else {
			}
		}
		return JsonUtils.toString(paramMap);
	}

	public static String toPostMethodJson(HttpServletRequest request) {
		InputStream in = null;
		ByteArrayOutputStream bin = null;
		String json = null;
		try {
			in = request.getInputStream();
			bin = new ByteArrayOutputStream(256);
			int b = 0;
			while ((b = in.read()) != -1) {
				bin.write(b);
			}
			json = bin.toString(CODE_UTF8);
			
			LogManager.getLogger().debug(json);//TODO
			
			if (!json.startsWith("{") && json.indexOf('=') != -1) {
				json = URLDecoder.decode(json, "utf-8");
				Map<String, String> paramMap = MapUtils.newHashMap();
				for (String kv : json.split("&")) {
					int index = kv.indexOf('=');
					if (index != -1) {
						String key = kv.substring(0, index);
						if (key.endsWith("[]")) {
							key = key.replace("[]", "");
						}
						String value = kv.substring(index + 1);
						paramMap.put(key, value);
					}
				}
				json = JSONObject.fromObject(paramMap).toString();
			}
		} catch (IOException e) {
			// TODO log.error("input stream error", e);
		} finally {
			try {
				if (null != in) {
					in.close();
				}
				if (null != bin) {
					bin.close();
				}
			} catch (IOException e) {

			}
		}
		return toJsonAlias(json);
	}

	public static void convertorRequest(MessageContext mc) {
		ServiceMap.Bean context = ServiceMap.getBeanContext().get(mc.getMapKey());
		Request req = JsonUtils.toBeanDefNew(mc.getReqBody(), context.getReq(), JSONALIASMAP);
		mc.setReq(req);
	}

	public static MessageHeader convertorHeader(HttpServletRequest req, MessageContext mc) {
		MessageHeader header = new MessageHeader();
		header.setVersion(req.getHeader(VERSION));
		header.setComponentType(req.getHeader(COMPONENTTYPE));
		header.setComponentCode(req.getHeader(COMPONENTCODE));
		header.setAuthenticateCode(req.getHeader(AUTHENTICATECODE));
		header.setTimeStamp(req.getHeader(TIMESTAMP));

		if (StringUtils.isBlank(header.getAuthenticateCode())) {
			header.setAuthenticateCode(req.getParameter(AUTHENTICATECODE));
		}
		if (StringUtils.isBlank(header.getAuthenticateCode())) {
			String jsonStr = toPostMethodJson(req);
			if (StringUtils.isNotBlank(jsonStr)) {
				mc.setReqBody(jsonStr);
				JSONObject json = JSONObject.fromObject(jsonStr);
				if (null != json && json.has(AUTHENTICATECODE)) {
					header.setAuthenticateCode(json.getString(AUTHENTICATECODE));
				}
			}
		}
		return header;
	}

	private static String toJsonAlias(String json) {
		for (Map.Entry<String, String> entry : JSON_ALIAS.entrySet()) {
			for (int i = 0; json.indexOf(entry.getKey()) > -1 && i < 100; i++) {
				json = json.replace(entry.getKey(), entry.getValue());
			}
		}
		return json;
	}
}
