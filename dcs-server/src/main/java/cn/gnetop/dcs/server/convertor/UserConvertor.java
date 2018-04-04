package cn.gnetop.dcs.server.convertor;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import cn.gnetop.dcs.server.service.userservice.schema.User;

public class UserConvertor {
	public static User convertor(cn.gnetop.dcs.dao.schema.User user) {
		if (null != user) {
			User u = new User();
			u.setId(user.getId());
			u.setUserid(user.getUserid());
			u.setUsername(user.getUsername());
			u.setPassword(user.getPassword());
			u.setNickname(user.getNickname());
			u.setMobile(user.getMobile());
			u.setLat(user.getLat());
			u.setLng(user.getLng());
			u.setDeviceToken(user.getDeviceToken());
			u.setToken(user.getToken());
			u.setLastLoginTime(user.getLastLoginTime());
			u.setCreateTime(user.getCreateTime());
			u.setUpdateTime(user.getUpdateTime());
			u.setIsGuest(user.getIsGuest());
			return u;
		}
		return null;
	}

	public static cn.gnetop.dcs.dao.schema.User convertor(User user) {
		if (null != user) {
			cn.gnetop.dcs.dao.schema.User u = new cn.gnetop.dcs.dao.schema.User();
			u.setId(user.getId());
			u.setUserid(user.getUserid());
			u.setUsername(user.getUsername());
			u.setPassword(user.getPassword());
			u.setNickname(user.getNickname());
			u.setMobile(user.getMobile());
			u.setLat(user.getLat());
			u.setLng(user.getLng());
			u.setDeviceToken(user.getDeviceToken());
			u.setToken(user.getToken());
			u.setLastLoginTime(user.getLastLoginTime());
			u.setCreateTime(user.getCreateTime());
			u.setUpdateTime(user.getUpdateTime());
			u.setIsGuest(user.getIsGuest());
			return u;
		}
		return null;
	}

	public static List<User> convertor(List<cn.gnetop.dcs.dao.schema.User> userList) {
		if (CollectionUtils.isNotEmpty(userList)) {
			List<User> uList = new ArrayList<>();
			for (cn.gnetop.dcs.dao.schema.User user : userList) {
				uList.add(convertor(user));
			}
			return uList;
		}
		return null;
	}

}
