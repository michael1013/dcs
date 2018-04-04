package cn.gnetop.dcs.dao;

import java.util.List;

import cn.gnetop.dcs.dao.base.BaseDao;
import cn.gnetop.dcs.dao.schema.CoinIncrease;

public interface CoinIncreaseDao extends BaseDao<CoinIncrease> {

	int batchInsert(List<CoinIncrease> list);
	
}
