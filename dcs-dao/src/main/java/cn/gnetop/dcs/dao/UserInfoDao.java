package cn.gnetop.dcs.dao;

import java.util.Map;

import cn.gnetop.dcs.dao.base.BaseDao;
import cn.gnetop.dcs.dao.schema.UserInfo;

public interface UserInfoDao extends BaseDao<UserInfo> {

	Object findUserDataByDate(Map<String, Object> paramMap);
	
}
