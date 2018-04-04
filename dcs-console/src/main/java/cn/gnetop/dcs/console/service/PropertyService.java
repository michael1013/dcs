package cn.gnetop.dcs.console.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.gnetop.dcs.dao.PropertyDao;
import cn.gnetop.dcs.dao.base.Page;
import cn.gnetop.dcs.dao.schema.Property;

@Service
public class PropertyService {

	@Autowired
	private PropertyDao propertyDao;

	public Page<Property> findPage(Property property) {
		Integer total = propertyDao.countFindList(property);
		Integer pageNo = property.getPageNo();
		Integer pageSize = property.getPageSize();
		Integer start = (pageNo - 1) * pageSize;
		property.setStart(start);
		Page<Property> loginPage = new Page<>();
		List<Property> list = propertyDao.findList(property);
		loginPage.setList(list);
		loginPage.setTotal(total);
		loginPage.setStart(start);
		loginPage.setPageNo(pageNo);
		loginPage.setPageSize(pageSize);
		return loginPage;
	}

}
