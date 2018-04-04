package cn.gnetop.dcs.dao.provider;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

import org.apache.ibatis.jdbc.SQL;

import cn.gnetop.dcs.dao.base.DataEntity;

public class DataEntityUpdateProvider {

	public String insert(DataEntity entity) {
		ProviderEntityMap pm = ProviderHelper.getEntityMethods(entity);
		Map<String, String> columnMap = pm.getColumnMap();
		Map<String, Method> methodMap = pm.getMethodMap();
		SQL sql = new SQL();
		sql.INSERT_INTO(entity.getTableName());
		for (Map.Entry<String, String> entry : columnMap.entrySet()) {
			try {
				// where条件字段值为空则跳过
				// 参考xml配置的<if test="column != null and column != ''"></if>
				Object o = methodMap.get(entry.getValue()).invoke(entity, null);
				if (null != o) {
					sql.VALUES(entry.getKey(), entry.getValue());
				}
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				e.printStackTrace();
			}

		}
		return sql.toString();
	}

	public String update(DataEntity entity) {
		ProviderEntityMap pm = ProviderHelper.getEntityMethods(entity);
		Map<String, String> columnMap = pm.getColumnMap();
		Map<String, Method> methodMap = pm.getMethodMap();
		SQL sql = new SQL();
		sql.UPDATE(entity.getTableName());
		for (Map.Entry<String, String> entry : columnMap.entrySet()) {
			try {
				// where条件字段值为空则跳过
				// 参考xml配置的<if test="column != null and column != ''"></if>
				Object o = methodMap.get(entry.getValue()).invoke(entity, null);
				if (null != o) {
					sql.SET(entry.getKey() + "=#{" + entry.getValue() + '}');
				}
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				e.printStackTrace();
			}
		}

		sql.WHERE(pm.getGeneratedKeys() + "= #{" + columnMap.get(pm.getGeneratedKeys()) + '}');
		return sql.toString();
	}

}
