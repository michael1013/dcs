package cn.gnetop.dcs.dao;

import java.util.List;

import cn.gnetop.dcs.dao.base.BaseDao;
import cn.gnetop.dcs.dao.schema.CurrencyRate;

public interface CurrencyRateDao extends BaseDao<CurrencyRate> {
	List<CurrencyRate> findAll();
}
