package cn.gnetop.pde.foundation.google.map;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import cn.gnetop.pde.foundation.HttpUtils;
import cn.gnetop.pde.foundation.MapUtils;
import cn.gnetop.pde.foundation.config.GlobalConfig;

public class GoogleMapClient {

	private static final Logger logger = Logger.getLogger(GoogleMapClient.class);

	static final String GOOGLE_GEOCODING_URL = GlobalConfig.getConfig("google.map.geocoding.url",
			"https://maps.googleapis.com/maps/api/geocode/json");

	static final String LANGUAGE_CN = "zh-CN";

	static final String RESULT_TYPE = GlobalConfig.getConfig("google.map.resultType", "locality");

	private static final String GOOGLE_APIKEY = GlobalConfig.getConfig("google.map.api.key",
			"AIzaSyA_LOWfdPymvPDy-4Vegfd-KnMIDQeZT1Y");

	private static final String PLAT_GOOGLE = "google";

	private static GoogleMapClient instance = new GoogleMapClient();

	private GoogleMapClient() {
	}

	static GoogleMapClient getInstance() {
		return instance;
	}

	String doGet(String url, Map<String, String> paramMap) {
		if (MapUtils.isNotEmpty(paramMap) && StringUtils.isBlank(paramMap.get("key"))) {
			paramMap.put("key", GOOGLE_APIKEY);
		}
		return HttpUtils.doGet(url, PLAT_GOOGLE, null, paramMap);
	}

	String doPost(String url, String jsonBody) {
		return HttpUtils.doPost(url, PLAT_GOOGLE, null, jsonBody);
	}
}
