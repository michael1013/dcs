package cn.gnetop.dcs.dao.provider;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

import org.apache.ibatis.jdbc.SQL;

import cn.gnetop.dcs.dao.base.DataEntity;

public class DataEntitySelectProvider {

	// private static final Map<String, Method> METHODMAP = new HashMap<>();

	public String findOne(DataEntity entity) {
		return find(entity, false);
	}

	public String findList(DataEntity entity) {
		return find(entity, false);
	}

	public String countFindList(DataEntity entity) {
		return find(entity, true);
	}

	public String find(DataEntity entity, boolean isCount) {
		ProviderEntityMap pm = ProviderHelper.getEntityMethods(entity);
		Map<String, String> columnMap = pm.getColumnMap();
		Map<String, Method> methodMap = pm.getMethodMap();

		SQL sql = new SQL();
		if (isCount) {
			sql.SELECT("COUNT(1)");
		}
		for (Map.Entry<String, String> entry : columnMap.entrySet()) {
			if (!isCount) {
				sql.SELECT(entry.getKey() + " AS `" + entry.getValue() + '`');
			}
			try {
				Object o = methodMap.get(entry.getValue()).invoke(entity, null);
				// where条件字段值为空则跳过
				// 参考xml配置的<if test="column != null and column != ''"></if>
				if (null != o) {
					sql.WHERE(entry.getKey() + "=#{" + entry.getValue() + '}');
				}
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				e.printStackTrace();
			}

		}
		sql.FROM(entity.getTableName());

		// 拼接分页参数
		StringBuffer sbsql = new StringBuffer();
		sbsql.append('(').append(sql).append(')');
		if (!isCount && null != entity.getPageSize() && !entity.getPageSize().equals(new Integer(0))) {
			sbsql.append("LIMIT ").append(entity.getStart()).append(',').append(entity.getPageSize());
		}
		return sbsql.toString();
	}

}
