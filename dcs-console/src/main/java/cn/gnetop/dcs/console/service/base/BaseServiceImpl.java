package cn.gnetop.dcs.console.service.base;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import cn.gnetop.dcs.dao.base.DataEntity;
import cn.gnetop.dcs.dao.base.IBaseMapper;
import cn.gnetop.dcs.dao.base.Page;

public abstract class BaseServiceImpl<T extends DataEntity, D extends IBaseMapper<T>> implements BaseService<T> {

	@Autowired
	protected D dao;

	public Page<T> findPage(T t) {
		Integer total = dao.countFindList(t);
		Integer pageNo = t.getPageNo();
		Integer pageSize = t.getPageSize();
		Integer start = (pageNo - 1) * pageSize;
		t.setStart(start);
		Page<T> page = new Page<>();
		List<T> list = dao.findList(t);
		page.setList(list);
		page.setTotal(total);
		page.setStart(start);
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		return page;
	}

	public Page<T> findAllPage(T t) {
		Integer total = dao.countFindList(t);
		Page<T> page = new Page<>();
		List<T> list = dao.findAll(t);
		page.setList(list);
		page.setTotal(total);
		return page;
	}

	public T findOne(T t) {
		return dao.findOne(t);
	}

	public int insert(T t) {
		return dao.insert(t);
	}

	public int update(T t) {
		return dao.update(t);
	}

}
