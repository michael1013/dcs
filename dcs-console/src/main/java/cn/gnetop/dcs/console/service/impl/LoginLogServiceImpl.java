package cn.gnetop.dcs.console.service.impl;

import java.util.Map;

import org.springframework.stereotype.Service;

import cn.gnetop.dcs.console.service.LoginLogService;
import cn.gnetop.dcs.console.service.base.BaseServiceImpl;
import cn.gnetop.dcs.dao.mapper.LoginLogMapperDaoImpl;
import cn.gnetop.dcs.dao.schema.LoginLog;

@Service
public class LoginLogServiceImpl extends BaseServiceImpl<LoginLog, LoginLogMapperDaoImpl> implements LoginLogService {

	public Map<String, String> findActivity(LoginLog l) {
		return dao.findActivity(l);
	}

}
