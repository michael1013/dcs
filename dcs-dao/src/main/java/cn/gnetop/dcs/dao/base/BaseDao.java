package cn.gnetop.dcs.dao.base;

import java.util.List;

public interface BaseDao<T> {
	
	T findOne(T t);

	Integer countFindList(T t);

	List<T> findList(T t);
	
	List<T> findAll(T t);

	int insert(T t);

	int update(T t);
	
	int batchInsert(List<T> t);
}
