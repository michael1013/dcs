package cn.gnetop.dcs.dao.impl;

import java.util.Map;

import org.springframework.stereotype.Repository;

import cn.gnetop.dcs.dao.UserInfoDao;
import cn.gnetop.dcs.dao.base.BaseDaoImpl;
import cn.gnetop.dcs.dao.schema.UserInfo;

@Repository("userInfoDao")
public class UserInfoDaoImpl extends BaseDaoImpl<UserInfo> implements UserInfoDao {

	@Override
	public Object findUserDataByDate(Map<String, Object> paramMap) {
		return sqlSessionTemplate.selectList(sqlNamespace + ".findUserDataByDate", paramMap);
	}

}
