package cn.gnetop.dcs.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import cn.gnetop.dcs.dao.UserLoginDao;
import cn.gnetop.dcs.dao.base.BaseDaoImpl;
import cn.gnetop.dcs.dao.schema.ServerInfo;
import cn.gnetop.dcs.dao.schema.UserData;
import cn.gnetop.dcs.dao.schema.UserLogin;

@Repository("userLoginDao")
public class UserLoginDaoImpl extends BaseDaoImpl<UserLogin> implements UserLoginDao {

	@Override
	public List<Map<String, Object>> findNew(ServerInfo server) {
		return sqlSessionTemplate.selectList(sqlNamespace + ".findNew", server);
	}

	@Override
	public Map<String, Object> findStay(Map<String, Object> param) {
		return sqlSessionTemplate.selectOne(sqlNamespace + ".findStay", param);
	}

	@Override
	public List<Map<String, Object>> findActive(ServerInfo server) {
		return sqlSessionTemplate.selectList(sqlNamespace + ".findActive", server);
	}

	@Override
	public UserData findKpi(Map<String, Object> paramMap) {
		return sqlSessionTemplate.selectOne(sqlNamespace + ".findKpi", paramMap);
	}

	@Override
	public UserData findMonthKpi(Map<String, Object> paramMap) {
		return sqlSessionTemplate.selectOne(sqlNamespace + ".findMonthKpi", paramMap);
	}

	@Override
	public UserData findGameKpi(Map<String, Object> paramMap) {
		return sqlSessionTemplate.selectOne(sqlNamespace + ".findGameKpi", paramMap);
	}

	@Override
	public UserData findGameMonthKpi(Map<String, Object> paramMap) {
		return sqlSessionTemplate.selectOne(sqlNamespace + ".findGameMonthKpi", paramMap);
	}

}
