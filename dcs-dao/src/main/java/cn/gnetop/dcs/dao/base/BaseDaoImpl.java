package cn.gnetop.dcs.dao.base;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;

public abstract class BaseDaoImpl<T> {

	protected String sqlNamespace;

	public BaseDaoImpl() {
		Class<?> entityClass = (Class<?>) ((ParameterizedType) getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];
		sqlNamespace = entityClass.getName();
	}

	@Resource
	protected SqlSessionTemplate sqlSessionTemplate;

	public T findOne(T t) {
		return sqlSessionTemplate.selectOne(sqlNamespace + ".findOne", t);
	}

	public Integer countFindList(T t) {
		return sqlSessionTemplate.selectOne(sqlNamespace + ".countFindList", t);
	}
	
	public List<T> findList(T t) {
		return sqlSessionTemplate.selectList(sqlNamespace + ".findList", t);
	}
	
	public List<T> findAll(T t) {
		return sqlSessionTemplate.selectList(sqlNamespace + ".findAll", t);
	}

	public int insert(T t) {
		return sqlSessionTemplate.insert(sqlNamespace + ".insert", t);
	}

	public int update(T t) {
		return sqlSessionTemplate.update(sqlNamespace + ".update", t);
	}
	
	public int batchInsert(List<T> t) {
		return sqlSessionTemplate.insert(sqlNamespace + ".batchInsert", t);
	}

	// public int delete(T t) {
	// return sqlSessionTemplate.delete(sqlNamespace + ".delete", t);
	// }
}
