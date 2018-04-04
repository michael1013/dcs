package cn.gnetop.dcs.system.auth;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import cn.gnetop.dcs.system.entity.MessageContext;
import cn.gnetop.dcs.system.entity.MessageHeader;
import cn.gnetop.dcs.system.entity.ResultCode;
import cn.gnetop.dcs.system.exception.DcsException;
import cn.gnetop.dcs.system.helper.ConfigManager;
import cn.gnetop.dcs.system.helper.MessageContextHelper;
import cn.gnetop.pde.foundation.EncrypteUtils;
import cn.gnetop.pde.foundation.config.GlobalConfig;

public class AccessAuthenticate {

	private static final String HEADER_ENCODE_KEY = GlobalConfig.getConfig("header.encode.key", "gNetopSZ");

	private static final int HEADER_ENCODE_EFFECT = GlobalConfig.getIntConfig("header.encode.effect", 10000);

	private static final String HEADER_AUTH_SWITCH = ConfigManager.getConfig("header_auth_switch", "ON");

	public static void auth(HttpServletRequest req, MessageContext mc) throws DcsException {
		MessageHeader mh = MessageContextHelper.convertorHeader(req, mc);
		mc.setHeader(mh);
		if (!headerAnalyse(mh)) {
			throw new DcsException(ResultCode.ACCESS_DENY, mh.getAuthenticateCode());
		}
	}

	private static boolean headerAnalyse(MessageHeader header) {
		if ("OFF".equalsIgnoreCase(HEADER_AUTH_SWITCH)) {
			return true;
		}
		try {
			String data = header.getAuthenticateCode();
			String srcCode = EncrypteUtils.desDecode(HEADER_ENCODE_KEY, data);
			if (StringUtils.isBlank(srcCode)) {
				return false;
			}
			String[] srcs = srcCode.split("\\|");
			if (srcs.length != 3) {
				return false;
			}
			// 2017-09-26 取消消息头时间校验
			// String date = srcs[2];
			// 加密串超过5分钟即失效
			// double dDate = DateUtils.getMillis() -
			// NumberUtils.toDouble(date);
			// if (dDate > HEADER_ENCODE_EFFECT) {
			// return false;
			// } else {
			return true;
			// }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
