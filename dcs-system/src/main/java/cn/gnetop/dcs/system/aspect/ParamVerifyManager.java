package cn.gnetop.dcs.system.aspect;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import cn.gnetop.dcs.system.entity.Request;
import cn.gnetop.dcs.system.entity.ResultCode;
import cn.gnetop.dcs.system.exception.DcsException;
import cn.gnetop.dcs.system.exception.ParamErrException;

public class ParamVerifyManager {

	private static List<String> SERVERID_LIST = new ArrayList<>();

	public static void init(List<String> serveridList) {
		SERVERID_LIST.clear();
		SERVERID_LIST.addAll(serveridList);
	}

	public static void verify(Request req) throws DcsException {
		verifyServerid(req);
	}

	private static void verifyServerid(Request req) throws DcsException {
		// 初始化服务器id列表为空则不做服务器id参数校验
		if (CollectionUtils.isEmpty(SERVERID_LIST)) {
			return;
		}
		for (Method m : req.getClass().getDeclaredMethods()) {
			if (m.getName().equals("getServerid")) {
				try {
					Object o = m.invoke(req, null);
					if (null != o) {
						String serverid = String.valueOf(o);
						if (SERVERID_LIST.indexOf(serverid) > -1) {
							return;
						}
					}
					throw new ParamErrException(ResultCode.PARAM_SERVERID_ERR);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

}
