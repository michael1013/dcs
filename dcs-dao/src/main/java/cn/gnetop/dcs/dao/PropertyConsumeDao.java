package cn.gnetop.dcs.dao;

import java.util.List;

import cn.gnetop.dcs.dao.base.BaseDao;
import cn.gnetop.dcs.dao.schema.PropertyConsume;

public interface PropertyConsumeDao extends BaseDao<PropertyConsume> {
	
	int batchInsert(List<PropertyConsume> list);
	
}
