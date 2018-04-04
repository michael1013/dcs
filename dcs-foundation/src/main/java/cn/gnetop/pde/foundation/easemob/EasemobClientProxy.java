package cn.gnetop.pde.foundation.easemob;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;

import cn.gnetop.pde.foundation.ArrayUtils;
import cn.gnetop.pde.foundation.MapUtils;
import cn.gnetop.pde.foundation.StringUtils;
import cn.gnetop.pde.foundation.easemob.entity.EasemobChatRoom;
import cn.gnetop.pde.foundation.easemob.entity.EasemobException;
import cn.gnetop.pde.foundation.easemob.entity.EasemobUser;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class EasemobClientProxy {

	private static EasemobClient client = EasemobClient.getInstance();

	private static EasemobClientProxy instance = new EasemobClientProxy();

	private EasemobClientProxy() {
	}

	public static EasemobClientProxy getInstance() {
		return instance;
	}

	public String createChatRoom(EasemobChatRoom room) {
		String result = client.create(room, "/chatrooms");
		JSONObject jsonObject = JSONObject.fromObject(result);
		String id = jsonObject.getJSONObject("data").getString("id");
		return id;
	}

	public void deleteChatRoom(String easeid) throws EasemobException {
		EasemobChatRoom room = new EasemobChatRoom();
		String result = client.delete(room, "/chatrooms/" + easeid);
		JSONObject jsonObject = JSONObject.fromObject(result);
		try {
			jsonObject.getJSONObject("data").getBoolean("success");
		} catch (Exception e) {
			try {
				if (null != jsonObject.getString("error")) {
					client.delete(room, "/chatrooms/" + easeid);
					jsonObject = JSONObject.fromObject(result);
				}
				if (null != jsonObject.getString("error")) {
					throw new EasemobException(e);
				}
			} catch (Exception e2) {
				throw new EasemobException(e2);
			}
		}
	}

	public void updateChatRoom(EasemobChatRoom room) {
		client.update(room, "/chatrooms/" + room.getName());
	}

	public EasemobChatRoom[] getChatRooms() {
		return getChatRooms(null);
	}

	public EasemobChatRoom[] getChatRooms(String easeid) {
		String action = null;
		if (StringUtils.isBlank(easeid)) {
			action = "/chatrooms";
		} else {
			action = "/chatrooms/" + easeid;
		}
		EasemobChatRoom room = new EasemobChatRoom();
		String result = client.get(room, action);
		JSONObject jsonObject = JSONObject.fromObject(result);
		JSONArray jsonArray = jsonObject.getJSONArray("data");
		return (EasemobChatRoom[]) jsonArray.toArray(jsonArray, room.getClass());
	}

	public EasemobChatRoom getChatRoom(String easeid) throws EasemobException {
		EasemobChatRoom[] rooms = getChatRooms(easeid);
		if (ArrayUtils.isNotEmpty(rooms)) {
			return rooms[0];
		} else {
			return null;
		}
	}

	public EasemobUser createUser(EasemobUser user) throws EasemobException {
		String result = client.create(user, "/users");
		return client.getEntity(user, result);
	}

	public EasemobUser getUser(EasemobUser user) throws EasemobException {
		String result = client.get(user, "/users");
		return client.getEntity(user, result);
	}

	public String updateUser(EasemobUser user) {
		String result = client.update(user, "/users/" + user.getUsername());
		JSONObject jo = JSONObject.fromObject(result);
		Object error = jo.get("error");
		if (null != error) {
			String desc = jo.getString("error_description");
			return desc;
		}
		return "";
	}

	public boolean updatePassword(String username, String password) {
		String result = client.doPost("/users/" + username + "/password", " {\"newpassword\" :\"" + password + "\"}");
		JSONObject jo = JSONObject.fromObject(result);
		String r = jo.getString("action");
		return "set user password".equals(r);
	}

	public Map<String, EasemobChatRoom> getChatRoomMap() {
		Map<String, EasemobChatRoom> map = MapUtils.newHashMap();
		EasemobChatRoom[] rooms = getChatRooms();
		if (ArrayUtils.isNotEmpty(rooms)) {
			for (EasemobChatRoom room : rooms) {
				map.put(room.getId(), room);
			}
		}
		return map;
	}

	public List<EasemobChatRoom> getHotChatRooms() {
		return this.getHotChatRooms(0);
	}

	public List<EasemobChatRoom> getHotChatRooms(int count) {
		EasemobChatRoom[] allRooms = this.getChatRooms();
		if (ArrayUtils.isNotEmpty(allRooms)) {
			// 对所有房间进行成员数排序
			List<EasemobChatRoom> allRoomsList = Arrays.asList(allRooms);
			Collections.sort(allRoomsList, new Comparator<EasemobChatRoom>() {
				@Override
				public int compare(EasemobChatRoom r1, EasemobChatRoom r2) {
					return r2.getAffiliations_count() - r1.getAffiliations_count();
				}
			});
			if (0 == count) {
				return allRoomsList;
			} else {
				return allRoomsList.subList(0, count);
			}
		}
		return null;
	}

	public String joinChatRoom(String username, String roomid) {
		String action = "/chatrooms/" + roomid + "/users/" + username;
		String result = EasemobClient.getInstance().doPost(action, null);
		return result;
	}

	public void joinChatRoomBatch(List<String> usernameList, String roomid) {
		if (CollectionUtils.isNotEmpty(usernameList)) {
			String action = "/chatrooms/" + roomid + "/users";
			JSONObject jo = new JSONObject();
			JSONArray ja = JSONArray.fromObject(usernameList);
			jo.put("usernames", ja);
			EasemobClient.getInstance().doPost(action, jo.toString());
		}
	}
}
