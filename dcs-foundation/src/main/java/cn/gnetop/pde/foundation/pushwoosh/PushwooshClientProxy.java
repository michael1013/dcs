package cn.gnetop.pde.foundation.pushwoosh;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class PushwooshClientProxy {

	private static PushwooshClient client = PushwooshClient.getInstance();

	private static final String TYPE_BROADCAST = "1001";

	private static final String TYPE_ACTIVITY = "1002";

	public static String sendPush(String sendDate, String content, String data, List<String> devices) {
		try {
			JSONArray jsonArray = new JSONArray();
			if (CollectionUtils.isNotEmpty(devices)) {
				for (String device : devices) {
					jsonArray.add(device);
				}
			}
			// 默认为立即发送推送
			if (StringUtils.isBlank(sendDate)) {
				sendDate = "now";
			}
			JSONObject notification = new JSONObject();
			notification.put("send_date", sendDate);
			notification.put("content", content);
			notification.put("data", data);
			if (jsonArray.size() > 0) {
				notification.put("devices", jsonArray);
			}
			JSONArray notificationsArray = JSONArray.fromObject(notification);
			String result = client.sendPushNotify("createMessage", "notifications", notificationsArray);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void registerDevice(String token, String deviceToken) {
		JSONObject jo = new JSONObject();
		try {
			jo.put("application", PushwooshClient.APPLICATION_CODE);
			jo.put("push_token", token);
			jo.put("language", "en");
			jo.put("hwid", deviceToken);
			// jo.put("timezone", 3600);
			jo.put("device_type", 1);
			client.sendPush("registerDevice", jo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 发送直播推送
	 * 
	 * @param sendDate
	 * @param content
	 * @param object
	 * @param devices
	 * @throws Exception
	 */
	public static void sendBroadcastPush(String sendDate, String content, JSONObject object, List<String> devices)
			throws Exception {
		sendPush(sendDate, content,
				new JSONObject().element("type", TYPE_BROADCAST).element("broadcast", object).toString(), devices);
	}

	/**
	 * 发送活动推送
	 * 
	 * @param sendDate
	 * @param content
	 * @param url
	 * @param devices
	 * @throws Exception
	 */
	public static String sendActivityPush(String sendDate, String content, String url, List<String> devices)
			throws Exception {
		String result = sendPush(sendDate, content,
				new JSONObject().element("type", TYPE_ACTIVITY).element("activityUrl", url).toString(), devices);
		return result;
	}
}
