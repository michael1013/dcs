package cn.gnetop.dcs.dao;

import java.util.List;
import java.util.Map;

import cn.gnetop.dcs.dao.base.BaseDao;
import cn.gnetop.dcs.dao.schema.ServerInfo;
import cn.gnetop.dcs.dao.schema.UserData;
import cn.gnetop.dcs.dao.schema.UserLogin;

public interface UserLoginDao extends BaseDao<UserLogin> {

	List<Map<String, Object>> findNew(ServerInfo server);

	List<Map<String, Object>> findActive(ServerInfo server);

	Map<String, Object> findStay(Map<String, Object> paramMap);

	UserData findKpi(Map<String, Object> paramMap);

	UserData findMonthKpi(Map<String, Object> paramMap);

	UserData findGameKpi(Map<String, Object> paramMap);

	UserData findGameMonthKpi(Map<String, Object> paramMap);

}
