package cn.gnetop.dcs.server.service.logservice.handler;

import cn.gnetop.dcs.dao.CoinConsumeDao;
import cn.gnetop.dcs.dao.schema.CoinConsume;
import cn.gnetop.dcs.server.service.logservice.req.CoinConsumeReq;
import cn.gnetop.dcs.server.service.logservice.rsp.CoinConsumeRsp;
import cn.gnetop.dcs.system.listener.BeanUtils;
import cn.gnetop.pde.foundation.DateUtils;
import cn.gnetop.pde.foundation.encryte.MD5Utils;

public class CoinConsumeHandler {
	public CoinConsumeRsp coinConsume(CoinConsumeReq req) {
		CoinConsume consume = new CoinConsume();
		consume.setUserid(req.getUserid());
		consume.setServerid(req.getServerid());
		consume.setType(req.getType());
		consume.setName(req.getName());
		consume.setAmount(req.getAmount());
		consume.setConsume(req.getConsume());
		consume.setCreateTime(req.getCreateTime());
		consume.setLogTime(DateUtils.getDateString());
		consume.setMd5(MD5Utils.encrypt(req.toString()));

		CoinConsumeDao dao = BeanUtils.getBean(CoinConsumeDao.class);
		dao.insert(consume);

		CoinConsumeRsp rsp = new CoinConsumeRsp();
		return rsp;
	}
}
