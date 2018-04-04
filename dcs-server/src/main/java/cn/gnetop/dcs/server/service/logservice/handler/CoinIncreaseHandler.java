package cn.gnetop.dcs.server.service.logservice.handler;

import cn.gnetop.dcs.dao.CoinIncreaseDao;
import cn.gnetop.dcs.dao.schema.CoinIncrease;
import cn.gnetop.dcs.server.service.logservice.req.CoinIncreaseReq;
import cn.gnetop.dcs.server.service.logservice.rsp.CoinIncreaseRsp;
import cn.gnetop.dcs.system.listener.BeanUtils;
import cn.gnetop.pde.foundation.DateUtils;
import cn.gnetop.pde.foundation.encryte.MD5Utils;

public class CoinIncreaseHandler {
	public CoinIncreaseRsp coinIncrease(CoinIncreaseReq req) {
		CoinIncrease increase = new CoinIncrease();
		increase.setUserid(req.getUserid());
		increase.setServerid(req.getServerid());
		increase.setType(req.getType());
		increase.setName(req.getName());
		increase.setAmount(req.getAmount());
		increase.setIncrease(req.getIncrease());
		increase.setCreateTime(req.getCreateTime());
		increase.setLogTime(DateUtils.getDateString());
		increase.setMd5(MD5Utils.encrypt(req.toString()));

		CoinIncreaseDao dao = BeanUtils.getBean(CoinIncreaseDao.class);
		dao.insert(increase);

		CoinIncreaseRsp rsp = new CoinIncreaseRsp();
		return rsp;
	}
}
