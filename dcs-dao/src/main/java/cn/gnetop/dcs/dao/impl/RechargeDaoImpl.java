package cn.gnetop.dcs.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import cn.gnetop.dcs.dao.RechargeDao;
import cn.gnetop.dcs.dao.base.BaseDaoImpl;
import cn.gnetop.dcs.dao.schema.Recharge;
import cn.gnetop.dcs.dao.schema.RechargeRank;

@Repository("rechargeDao")
public class RechargeDaoImpl extends BaseDaoImpl<Recharge> implements RechargeDao {

	@Override
	public List<RechargeRank> findRank(Recharge recharge) {
		return sqlSessionTemplate.selectList(sqlNamespace + ".findRank", recharge);
	}

	@Override
	public Map<String, Object> findRechargeData(Map<String, Object> paramMap) {
		return sqlSessionTemplate.selectOne(sqlNamespace + ".findRechargeData", paramMap);
	}

	@Override
	public Map<String, Object> findGameRechargeData(Map<String, Object> paramMap) {
		return sqlSessionTemplate.selectOne(sqlNamespace + ".findGameRechargeData", paramMap);
	}

	@Override
	public List<Recharge> findTotalList(Recharge recharge) {
		return sqlSessionTemplate.selectList(sqlNamespace + ".findTotalList", recharge);
	}

}
