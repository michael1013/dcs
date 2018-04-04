package cn.gnetop.dcs.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.gnetop.dcs.dao.CoinIncreaseDao;
import cn.gnetop.dcs.dao.base.BaseDaoImpl;
import cn.gnetop.dcs.dao.schema.CoinIncrease;

@Repository("coinIncreaseDao")
public class CoinIncreaseDaoImpl extends BaseDaoImpl<CoinIncrease> implements CoinIncreaseDao {

	@Override
	public int batchInsert(List<CoinIncrease> list) {
		return sqlSessionTemplate.insert(sqlNamespace + ".batchInsert", list);
	}

}
