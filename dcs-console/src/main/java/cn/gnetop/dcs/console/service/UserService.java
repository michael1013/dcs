package cn.gnetop.dcs.console.service;

import java.util.List;
import java.util.Map;

import cn.gnetop.dcs.console.service.base.BaseService;
import cn.gnetop.dcs.dao.schema.User;

public interface UserService extends BaseService<User> {

	List<Map<String, String>> findNew(User user);

}
