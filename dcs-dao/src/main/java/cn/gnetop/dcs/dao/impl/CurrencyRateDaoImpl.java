package cn.gnetop.dcs.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.gnetop.dcs.dao.CurrencyRateDao;
import cn.gnetop.dcs.dao.base.BaseDaoImpl;
import cn.gnetop.dcs.dao.schema.CurrencyRate;

@Repository("CurrencyRateDao")
public class CurrencyRateDaoImpl extends BaseDaoImpl<CurrencyRate> implements CurrencyRateDao {

	@Override
	public List<CurrencyRate> findAll() {
		return sqlSessionTemplate.selectList(sqlNamespace + ".findAll");
	}

}
