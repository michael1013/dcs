package cn.gnetop.pde.foundation;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public final class JsonUtils {

	private static final Logger logger = Logger.getLogger(JsonUtils.class);

	public static <T> T toBean(String json, Class<T> clz) {
		return toBean(json, clz, null);
	}

	@SuppressWarnings("unchecked")
	public static <T> T toBean(String json, Class<T> clz, Map beanMap) {
		T obj = null;
		if (StringUtils.isNoneBlank(json) && null != clz) {
			JSONObject jsonObject = JSONObject.fromObject(json);
			obj = (T) JSONObject.toBean(jsonObject, clz, beanMap);
		}
		return obj;
	}

	public static <T> T toBeanDefNew(String json, Class<T> clz) {
		return toBeanDefNew(json, clz, null);
	}

	/**
	 * json转bean，若json格式为空则创建bean对象
	 * 
	 * @param json
	 * @param clz
	 * @return
	 */
	public static <T> T toBeanDefNew(String json, Class<T> clz, Map beanMap) {
		T t = toBean(json, clz, beanMap);
		if (null == t && null != clz) {
			try {
				t = clz.newInstance();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return t;
	}

	public static String toString(Object obj) {
		String json = null;
		if (null != obj) {
			JSONObject jsonObject = JSONObject.fromObject(obj);
			json = jsonObject.toString();
		}
		return json;
	}

	public static String toArrayString(List<?> objs) {
		String json = null;
		if (null != objs) {
			JSONArray jsonArray = JSONArray.fromObject(objs);
			json = jsonArray.toString();
		}
		return json;
	}

	public static String toArrayString(Object[] objs) {
		String json = null;
		if (null != objs) {
			JSONArray jsonArray = JSONArray.fromObject(objs);
			json = jsonArray.toString();
		}
		return json;
	}

}
