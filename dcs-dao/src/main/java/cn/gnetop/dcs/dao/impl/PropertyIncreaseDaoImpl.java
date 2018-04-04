package cn.gnetop.dcs.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.gnetop.dcs.dao.PropertyIncreaseDao;
import cn.gnetop.dcs.dao.base.BaseDaoImpl;
import cn.gnetop.dcs.dao.schema.PropertyIncrease;

@Repository("propertyIncreaseDao")
public class PropertyIncreaseDaoImpl extends BaseDaoImpl<PropertyIncrease> implements PropertyIncreaseDao {

	@Override
	public int batchInsert(List<PropertyIncrease> list) {
		return sqlSessionTemplate.insert(sqlNamespace + ".batchInsert", list);
	}

}
