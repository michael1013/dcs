package cn.gnetop.pde.foundation.easemob;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.log4j.Logger;

import cn.gnetop.pde.foundation.HttpUtils;
import cn.gnetop.pde.foundation.JsonUtils;
import cn.gnetop.pde.foundation.MapUtils;
import cn.gnetop.pde.foundation.StringUtils;
import cn.gnetop.pde.foundation.config.GlobalConfig;
import cn.gnetop.pde.foundation.easemob.entity.EasemobEntity;
import cn.gnetop.pde.foundation.easemob.entity.EasemobException;
import cn.gnetop.pde.foundation.easemob.entity.EasemobTokenRsp;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class EasemobClient {

	private static final Logger logger = Logger.getLogger(EasemobClient.class);
	private static final String PLAT_EASEMOB = "easemob";
	private static final String EASEMOBURL = GlobalConfig.getConfig("easemob.url", "https://a1.easemob.com");
	private static final String ORG = GlobalConfig.getConfig("easemob.org", "1139161117178779");
	private static final String COMP = GlobalConfig.getConfig("easemob.comp", "livobo");
	private static final String COMMON_URL = EASEMOBURL + "/" + ORG + "/" + COMP;
	private static final String TOKEN_REQ_BODY = GlobalConfig.getConfig("easemob.token.reqbody",
			"{\"grant_type\":\"client_credentials\",\"client_id\":\"YXA6Ud8wQJpjEearEJXxDaDH8A\",\"client_secret\":\"YXA6LHE_p_KneNtKTQl0rK4ny_iOFXs\"}");

	private static String token = "YWMt3FGqwqxwEeanri19a8Zq_AAAAAAAAAAAAAAAAAAAAAHbcyWQrHAR5pzQ84x617JzAgMAAAFYcDMEGwBPGgACA-Tu8P6EiVjS5GE-kCVP7V2rhdwWe-_uWKIycnz4_A";

	@SuppressWarnings("unchecked")
	private static final Map<String, String> header = MapUtils.newHashMap();

	private EasemobClient() {
		this.getToken();
	}

	private static EasemobClient instance = new EasemobClient();

	static EasemobClient getInstance() {
		return instance;
	}

	private void getToken() {
		if (StringUtils.isBlank(token)) {
			String url = COMMON_URL + "/token";
			String resultJson = HttpUtils.doPost(url, null, TOKEN_REQ_BODY);
			EasemobTokenRsp rsp = JsonUtils.toBean(resultJson, EasemobTokenRsp.class);
			if (null != rsp) {
				token = rsp.getAccess_token();
				System.out.println("token:" + token);
			} else {
				logger.error("get easemob token failed");
			}
		}
		header.put("Authorization", "Bearer " + token);
	}

	<T> String sendRequest(T t, String method, String action) {
		if (null != t) {
			String jsonReq = JsonUtils.toString(t);
			String requestUrl = COMMON_URL + (action.startsWith("/") ? action : "/" + action);
			String jsonResult = HttpUtils.sendHttpRequest(requestUrl, method, PLAT_EASEMOB, header, jsonReq);
			return jsonResult;
		}
		logger.error("request entity is null");
		return null;
	}

	<T> String create(T t, String action) {
		String jsonResult = this.sendRequest(t, HttpUtils.POST, action);
		return jsonResult;
	}

	<T> String get(T t, String action) {
		String jsonResult = sendRequest(t, HttpUtils.GET, action);
		return jsonResult;
	}

	<T> String delete(T t, String action) {
		String jsonResult = this.sendRequest(t, HttpUtils.DELETE, action);
		return jsonResult;
	}

	<T> String update(T t, String action) {
		String jsonResult = this.sendRequest(t, HttpUtils.PUT, action);
		return jsonResult;
	}

	<T> T[] getEntitys(T t, String json) throws EasemobException {
		EasemobEntity<T> entity = JsonUtils.toBean(json, EasemobEntity.class);
		if (null == entity) {
			logger.error("entity is null");
			throw new EasemobException();
		}
		Map<String, Object> jsonMap = JSONObject.fromObject(json);
		Object exception = jsonMap.get("exception");
		if (null == entity || null != exception) {
			logger.error(entity.getError_description());
			throw new EasemobException(entity);
		}
		JSONArray jsonArray = JSONArray.fromObject(jsonMap.get("entities"));
		return (T[]) jsonArray.toArray(jsonArray, t.getClass());
	}

	<T> T getEntity(T t, String json) throws EasemobException {
		T[] array = getEntitys(t, json);
		if (ArrayUtils.isNotEmpty(array)) {
			return array[0];
		} else {
			return null;
		}
	}

	<T> Map<String, String> getData(T t, String json) throws EasemobException {
		EasemobEntity<T> entity = JsonUtils.toBean(json, EasemobEntity.class);
		if (null == entity) {
			logger.error("entity is null");
			throw new EasemobException();
		}
		Map<String, Object> jsonMap = JSONObject.fromObject(json);
		Object exception = jsonMap.get("exception");
		if (null == entity || null != exception) {
			logger.error(entity.getError_description());
			throw new EasemobException(entity);
		}
		JSONObject jsonObject = JSONObject.fromObject(jsonMap.get("data"));
		return (Map<String, String>) JSONObject.toBean(jsonObject, HashMap.class);
	}

	String doPost(String url, String jsonReq) {
		String requestUrl = COMMON_URL + (url.startsWith("/") ? url : "/" + url);
		return HttpUtils.sendHttpRequest(requestUrl, HttpUtils.POST, PLAT_EASEMOB, header, jsonReq);
	}
}
