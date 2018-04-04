package cn.gnetop.dcs.server.service.logservice.handler;

import cn.gnetop.dcs.server.init.ServerInitManager;
import cn.gnetop.dcs.server.service.logservice.req.FlushConfigReq;
import cn.gnetop.dcs.server.service.logservice.rsp.FlushConfigRsp;
import cn.gnetop.dcs.system.entity.ResultCode;
import cn.gnetop.dcs.system.exception.DcsException;
import cn.gnetop.pde.foundation.StringUtils;
import cn.gnetop.pde.foundation.encryte.DESBaseUtils;
import net.sf.json.JSONObject;

public class FlushConfigHandler {
	public FlushConfigRsp flushConfig(FlushConfigReq req) throws DcsException {
		String k = req.getK();
		String v = req.getV();
		String x = req.getX();
		if (StringUtils.hasBlank(k, v, x)) {
			throw new DcsException(ResultCode.ACCESS_DENY);
		}
		FlushConfigRsp rsp = new FlushConfigRsp();
		try {
			if (v.equals(DESBaseUtils.encrypt(k, "gNetopSZ2017"))) {
				if ("server".equals(x)) {
					ServerInitManager.paramVerifyInit();
				} else if ("config".equals(x)) {
					ServerInitManager.configInit();
				}
			} else {
				JSONObject j = new JSONObject();
				j.put("msg", "v error");
				rsp.setData(j);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rsp;
	}
}
