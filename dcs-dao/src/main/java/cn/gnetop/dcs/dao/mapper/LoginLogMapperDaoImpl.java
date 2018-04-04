package cn.gnetop.dcs.dao.mapper;

import java.util.Map;

import org.springframework.stereotype.Repository;

import cn.gnetop.dcs.dao.base.BaseMapperDao;
import cn.gnetop.dcs.dao.schema.LoginLog;

@Repository
public class LoginLogMapperDaoImpl extends BaseMapperDao<LoginLog, LoginLogMapper> implements LoginLogMapper {

	@Override
	public Map<String, String> findActivity(LoginLog l) {
		return null;
	}

}
