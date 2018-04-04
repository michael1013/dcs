package cn.gnetop.dcs.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.gnetop.dcs.dao.ConfigDao;
import cn.gnetop.dcs.dao.base.BaseDaoImpl;
import cn.gnetop.dcs.dao.schema.Config;

@Repository("configDao")
public class ConfigDaoImpl extends BaseDaoImpl<Config> implements ConfigDao {

	@Override
	public List<Config> findAll() {
		return sqlSessionTemplate.selectList(sqlNamespace + ".findAll");
	}

}
