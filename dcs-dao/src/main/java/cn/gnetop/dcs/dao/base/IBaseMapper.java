package cn.gnetop.dcs.dao.base;

import java.util.List;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.SelectProvider;

import cn.gnetop.dcs.dao.provider.DataEntitySelectProvider;
import cn.gnetop.dcs.dao.provider.DataEntityUpdateProvider;

public interface IBaseMapper<T> {

	@One
	@SelectProvider(type = DataEntitySelectProvider.class, method = "findOne")
	T findOne(T t);

	@SelectProvider(type = DataEntitySelectProvider.class, method = "countFindList")
	Integer countFindList(T t);

	@SelectProvider(type = DataEntitySelectProvider.class, method = "findList")
	List<T> findList(T t);

	@SelectProvider(type = DataEntitySelectProvider.class, method = "findList")
	List<T> findAll(T t);

	@InsertProvider(type = DataEntityUpdateProvider.class, method = "insert")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	int insert(T t);

	@InsertProvider(type = DataEntityUpdateProvider.class, method = "update")
	int update(T t);

}
