package cn.gnetop.dcs.dao.provider;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import cn.gnetop.dcs.dao.base.DataEntity;
import cn.gnetop.pde.foundation.StringUtils;

public class ProviderHelper {

	public static ProviderEntityMap getEntityMethods(DataEntity entity) {
		Map<String, String> columnMap = new HashMap<>();
		Map<String, Method> methodMap = new HashMap<>();
		ProviderEntityMap pm = new ProviderEntityMap();
		pm.setColumnMap(columnMap);
		pm.setMethodMap(methodMap);
		for (Field f : entity.getClass().getDeclaredFields()) {
			Column c = f.getDeclaredAnnotation(Column.class);
			String column = null;
			if (null != c) {
				if (StringUtils.isNotBlank(c.column())) {
					column = c.column();
				} else if (c.humpToLine()) {
					column = StringUtils.humpToLine(f.getName());
				} else {
					column = f.getName();
				}
				columnMap.put(column, f.getName());

				try {
					methodMap.put(f.getName(),
					        entity.getClass().getMethod("get" + StringUtils.capitalize(f.getName()), null));
				} catch (NoSuchMethodException | SecurityException e) {
					e.printStackTrace();
				}
			}

			GeneratedKeys k = f.getDeclaredAnnotation(GeneratedKeys.class);
			if (StringUtils.isBlank(pm.getGeneratedKeys()) && null != k) {
				pm.setGeneratedKeys(column);
			}
		}
		
		if (StringUtils.isBlank(pm.getGeneratedKeys())) {
			pm.setGeneratedKeys("id");
		}
		return pm;
	}

}

class ProviderEntityMap {
	private String generatedKeys;
	private Map<String, String> columnMap;
	private Map<String, Method> methodMap;

	public String getGeneratedKeys() {
		return generatedKeys;
	}

	public void setGeneratedKeys(String generatedKeys) {
		this.generatedKeys = generatedKeys;
	}

	public Map<String, String> getColumnMap() {
		return columnMap;
	}

	public void setColumnMap(Map<String, String> columnMap) {
		this.columnMap = columnMap;
	}

	public Map<String, Method> getMethodMap() {
		return methodMap;
	}

	public void setMethodMap(Map<String, Method> methodMap) {
		this.methodMap = methodMap;
	}

}