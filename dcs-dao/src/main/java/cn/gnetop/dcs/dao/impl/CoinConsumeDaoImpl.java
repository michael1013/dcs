package cn.gnetop.dcs.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.gnetop.dcs.dao.CoinConsumeDao;
import cn.gnetop.dcs.dao.base.BaseDaoImpl;
import cn.gnetop.dcs.dao.schema.CoinConsume;

@Repository("coinConsumeDao")
public class CoinConsumeDaoImpl extends BaseDaoImpl<CoinConsume> implements CoinConsumeDao {

	@Override
	public int batchInsert(List<CoinConsume> list) {
		return sqlSessionTemplate.insert(sqlNamespace + ".batchInsert", list);
	}

}
