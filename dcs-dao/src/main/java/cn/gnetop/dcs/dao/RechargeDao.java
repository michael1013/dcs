package cn.gnetop.dcs.dao;

import java.util.List;
import java.util.Map;

import cn.gnetop.dcs.dao.base.BaseDao;
import cn.gnetop.dcs.dao.schema.Recharge;
import cn.gnetop.dcs.dao.schema.RechargeRank;

public interface RechargeDao extends BaseDao<Recharge>{

	List<RechargeRank> findRank(Recharge recharge);

	Map<String, Object> findRechargeData(Map<String, Object> paramMap);
	
	Map<String, Object> findGameRechargeData(Map<String, Object> paramMap);

	List<Recharge> findTotalList(Recharge recharge);
	
}
