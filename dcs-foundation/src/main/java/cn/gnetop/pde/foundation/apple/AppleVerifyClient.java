package cn.gnetop.pde.foundation.apple;

import cn.gnetop.pde.foundation.HttpUtils;
import cn.gnetop.pde.foundation.config.GlobalConfig;
import net.sf.json.JSONObject;

public class AppleVerifyClient {
	private static final String PLAT_APPLE = "apple";

	private static final String APPLE_VERIFY_URL = GlobalConfig.getConfig("apple.verify.url",
			"https://buy.itunes.apple.com/verifyReceipt");

	public static String verifyList(String receipt) {
		JSONObject jo = new JSONObject();
		jo.put("receipt-data", receipt);
		String result = HttpUtils.doHttpsPost(APPLE_VERIFY_URL, PLAT_APPLE, jo.toString());
		return result;
	}
}
