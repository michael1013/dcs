package cn.gnetop.dcs.system.helper;

import java.util.Map;

import org.apache.commons.lang.StringUtils;

import cn.gnetop.dcs.system.entity.Result;
import cn.gnetop.dcs.system.entity.ResultCode;

public class ResultCodeManager {

	private static Map<String, Result> resultMap;

	public static void setResultMap(Map<String, Result> resultMap) {
		ResultCodeManager.resultMap = resultMap;
	}

	public static Result getResult(String resultCode) {
		if (StringUtils.isBlank(resultCode)) {
			resultCode = ResultCode.SYS_ERR;
		}
		return resultMap.get(resultCode);
	}

	public static Map<String, Result> getResultMap() {
		return resultMap;
	}
}
