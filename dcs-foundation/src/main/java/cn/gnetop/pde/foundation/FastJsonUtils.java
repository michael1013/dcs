package cn.gnetop.pde.foundation;

import com.alibaba.fastjson.JSONObject;

public class FastJsonUtils {
	public static String toString(Object o) {
		return JSONObject.toJSONString(o);
	}
}
