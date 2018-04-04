package cn.gnetop.dcs.console.service;

import java.util.Map;

import cn.gnetop.dcs.console.service.base.BaseService;
import cn.gnetop.dcs.dao.schema.LoginLog;

public interface LoginLogService extends BaseService<LoginLog> {

	Map<String, String> findActivity(LoginLog l);

}
