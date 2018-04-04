package cn.gnetop.dcs.dao.impl;

import org.springframework.stereotype.Repository;

import cn.gnetop.dcs.dao.PropertyDao;
import cn.gnetop.dcs.dao.base.BaseDaoImpl;
import cn.gnetop.dcs.dao.schema.Property;

@Repository("propertyDao")
public class PropertyDaoImpl extends BaseDaoImpl<Property> implements PropertyDao {

}
