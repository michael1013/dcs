package cn.gnetop.dcs.dao;

import java.util.List;

import cn.gnetop.dcs.dao.base.BaseDao;
import cn.gnetop.dcs.dao.schema.Config;

public interface ConfigDao extends BaseDao<Config>{
	List<Config> findAll();
}
