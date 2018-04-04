package cn.gnetop.dcs.dao.base;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;

public class BaseMapperDao<T, D extends IBaseMapper<T>> {

	protected D dao;

	protected String sqlNamespace;

	public BaseMapperDao() {
		Class<?> entityClass = (Class<?>) ((ParameterizedType) getClass().getGenericSuperclass())
		        .getActualTypeArguments()[0];
		sqlNamespace = entityClass.getName();
	}

	@Resource
	private SqlSessionTemplate sqlSessionTemplate;

	public SqlSessionTemplate getSqlSessionTemplate() {
		return sqlSessionTemplate;
	}

	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
		this.init();
	}

	private void init() {
		try {
			dao = sqlSessionTemplate.getMapper(
			        (Class<D>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1]);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public T findOne(T t) {
		return dao.findOne(t);
	}

	public Integer countFindList(T t) {
		return dao.countFindList(t);
	}

	public List<T> findList(T t) {
		return dao.findList(t);
	}

	public List<T> findAll(T t) {
		return dao.findAll(t);
	}

	public int insert(T t) {
		return dao.insert(t);
	}

	public int update(T t) {
		return dao.update(t);
	}

}
