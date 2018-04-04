package cn.gnetop.pde.foundation.google.map;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import cn.gnetop.pde.foundation.MapUtils;
import cn.gnetop.pde.foundation.config.GlobalConfig;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class GoogleMapClientProxy {

	private static GoogleMapClient client = GoogleMapClient.getInstance();

	private static String location_null_defname_en = GlobalConfig.getConfig("location.null.defname.en", "Mars");

	private static String location_null_defname_cn = GlobalConfig.getConfig("location.null.defname.cn", "火星");

	public static String doPost() {
		return null;
	}

	private static JSONArray getGeocodingLocation(Double x, Double y, String lang) {
		Map<String, String> paramMap = MapUtils.newHashMap();
		paramMap.put("latlng", new StringBuffer().append(x).append(',').append(y).toString());
		paramMap.put("result_type", GoogleMapClient.RESULT_TYPE);
		if (StringUtils.isNoneBlank(lang) && "cn".equalsIgnoreCase(lang)) {
			lang = GoogleMapClient.LANGUAGE_CN;
		}
		paramMap.put("language", lang);
		String result = client.doGet(GoogleMapClient.GOOGLE_GEOCODING_URL, paramMap);
		JSONObject jsonObject = JSONObject.fromObject(result);
		JSONArray jsonArray = jsonObject.getJSONArray("results");
		if (jsonArray.isEmpty()) {
			return null;
		} else {
			return jsonArray;
		}
	}

	private static JSONArray getGeocodingEn(Double x, Double y) {
		return getGeocodingLocation(x, y, null);
	}

	private static JSONArray getGeocodingCn(Double x, Double y) {
		return getGeocodingLocation(x, y, GoogleMapClient.LANGUAGE_CN);
	}

	public static Location getGeocoding(Double x, Double y) {
		JSONArray jArrayEn = getGeocodingEn(x, y);
		if (null == jArrayEn) {
			return new Location(location_null_defname_en, location_null_defname_cn);
		}
		String en = jArrayEn.getJSONObject(0).getJSONArray("address_components").getJSONObject(0)
				.getString("long_name");
		JSONArray jArrayCn = getGeocodingCn(x, y);
		if (null == jArrayCn) {
			return new Location(location_null_defname_en, location_null_defname_en);
		}
		String cn = jArrayCn.getJSONObject(0).getJSONArray("address_components").getJSONObject(0)
				.getString("long_name");
		return new Location(en, cn);
	}

	public static class Location {
		private String en;
		private String cn;

		Location() {
		}

		Location(String en, String cn) {
			this.en = en;
			this.cn = cn;
		}

		public String getEn() {
			return en;
		}

		public void setEn(String en) {
			this.en = en;
		}

		public String getCn() {
			return cn;
		}

		public void setCn(String cn) {
			this.cn = cn;
		}

	}
}
