package cn.gnetop.dcs.dao;

import java.util.List;

import cn.gnetop.dcs.dao.base.BaseDao;
import cn.gnetop.dcs.dao.schema.CoinConsume;

public interface CoinConsumeDao extends BaseDao<CoinConsume> {
	
	public int batchInsert(List<CoinConsume> list);
	
}
