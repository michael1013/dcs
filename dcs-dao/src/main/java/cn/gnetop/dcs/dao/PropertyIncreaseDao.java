package cn.gnetop.dcs.dao;

import java.util.List;

import cn.gnetop.dcs.dao.base.BaseDao;
import cn.gnetop.dcs.dao.schema.PropertyIncrease;

public interface PropertyIncreaseDao extends BaseDao<PropertyIncrease>{

	int batchInsert(List<PropertyIncrease> list);
	
}
