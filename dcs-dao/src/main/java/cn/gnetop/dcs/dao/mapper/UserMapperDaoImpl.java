package cn.gnetop.dcs.dao.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import cn.gnetop.dcs.dao.base.BaseMapperDao;
import cn.gnetop.dcs.dao.schema.User;

@Repository
public class UserMapperDaoImpl extends BaseMapperDao<User, UserMapper> implements UserMapper {

	public List<Map<String, String>> findNew(User user) {
		return dao.findNew(user);
	}

}
