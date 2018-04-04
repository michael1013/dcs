package cn.gnetop.pde.foundation.pushwoosh;

import java.net.URL;

import org.apache.commons.lang3.StringUtils;

import cn.gnetop.pde.foundation.HttpUtils;
import cn.gnetop.pde.foundation.config.GlobalConfig;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class PushwooshClient {

	public static final String PUSHWOOSH_SERVICE_BASE_URL = GlobalConfig.getConfig("pushwoosh.service.url",
			"https://cp.pushwoosh.com/json/1.3/");

	private static final String PUSHWOOSH_PLAT = "pushwoosh";

	static final String AUTH_TOKEN = GlobalConfig.getConfig("pushwoosh.api.token",
			"NMcy9mFxcw27XhT3FP485BPonth9hz0c7cDeGni6XowvL0bRPuN4dHZCw3NSeUKgaWWL594VDc6EcmycOdgs");

	static final String APPLICATION_CODE = GlobalConfig.getConfig("pushwoosh.app.code", "B7F6A-3DCCE");

	private static final PushwooshClient instance = new PushwooshClient();

	private PushwooshClient() {
	}

	static PushwooshClient getInstance() {
		return instance;
	}

	String sendPush(String action, JSONObject requestObject) throws Exception {
		URL url = new URL(PUSHWOOSH_SERVICE_BASE_URL + action);
		if (null == requestObject) {
			requestObject = new JSONObject();
			requestObject.put("application", PushwooshClient.APPLICATION_CODE);
			requestObject.put("auth", PushwooshClient.AUTH_TOKEN);
		}
		JSONObject mainRequest = new JSONObject().element("request", requestObject);
		String rsp = HttpUtils.sendHttpRequest(url.toString(), HttpUtils.POST, PUSHWOOSH_PLAT, null,
				mainRequest.toString());
		return rsp;
	}

	String sendPushNotify(String action, String key, JSONArray notificationArray) throws Exception {
		JSONObject requestObject = new JSONObject().element("application", APPLICATION_CODE);
		requestObject.element("auth", AUTH_TOKEN);
		if (StringUtils.isNoneBlank(key) && null != notificationArray) {
			requestObject.put(key, notificationArray);
		}
		return this.sendPush(action, requestObject);
	}
}
