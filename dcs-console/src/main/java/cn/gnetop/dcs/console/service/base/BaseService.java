package cn.gnetop.dcs.console.service.base;

import cn.gnetop.dcs.dao.base.DataEntity;
import cn.gnetop.dcs.dao.base.Page;

public interface BaseService<T extends DataEntity> {

	public Page<T> findPage(T t);

	public Page<T> findAllPage(T t);

	public T findOne(T t);

	public int insert(T t);

	public int update(T t);

}
