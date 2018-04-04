package cn.gnetop.dcs.console.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import cn.gnetop.dcs.console.service.UserService;
import cn.gnetop.dcs.console.service.base.BaseServiceImpl;
import cn.gnetop.dcs.dao.mapper.UserMapperDaoImpl;
import cn.gnetop.dcs.dao.schema.User;

@Service
public class UserServiceImpl extends BaseServiceImpl<User, UserMapperDaoImpl> implements UserService {

	@Override
	public List<Map<String, String>> findNew(User user) {

		// return mapper.findNew(user);
		return dao.findNew(user);
	}

}
