package cn.gnetop.dcs.system.auth;

import cn.gnetop.dcs.system.entity.MessageContext;
import cn.gnetop.dcs.system.entity.ResultCode;
import cn.gnetop.dcs.system.exception.DcsException;
import cn.gnetop.dcs.system.helper.ConfigManager;
import cn.gnetop.pde.foundation.encryte.MD5Utils;
import net.sf.json.JSONObject;

public class ParamAntiTemper {

	private static final String PARAM_ANTITEMPER_SWITCH = ConfigManager.getConfig("param_antitemper_switch", "ON");

	public static void auth(MessageContext mc) throws DcsException {
		if ("OFF".endsWith(PARAM_ANTITEMPER_SWITCH)) {
			return;
		}
		String req = mc.getReqBody();
		JSONObject jo = JSONObject.fromObject(req);
		StringBuffer sb = new StringBuffer();
		for (Object entry : jo.entrySet()) {
			if (String.valueOf(entry).startsWith("vk=")) {
				break;
			} else {
				sb.append(entry).append('&');
			}
		}
		String vk = sb.substring(0, sb.length() - 1);
		String vkmd5 = MD5Utils.encrypt(vk + "&gNetopSZ");
		if (jo.containsKey("vk") && jo.getString("vk").equals(vkmd5)) {
			return;
		} else {
			throw new DcsException(ResultCode.PARAM_TEMPER);
		}
	}

	public static void main(String[] args) {
		MessageContext mc = new MessageContext();
		mc.setReqBody(
				"{\"username\":\"name015\",	\"vk\":\"\"}");
		try {
			auth(mc);
		} catch (DcsException e) {
			e.printStackTrace();
		}
	}
}
