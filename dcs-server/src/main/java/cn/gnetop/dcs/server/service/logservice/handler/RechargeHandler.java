package cn.gnetop.dcs.server.service.logservice.handler;

import cn.gnetop.dcs.dao.RechargeDao;
import cn.gnetop.dcs.dao.ServerInfoDao;
import cn.gnetop.dcs.dao.schema.Recharge;
import cn.gnetop.dcs.dao.schema.ServerInfo;
import cn.gnetop.dcs.server.service.logservice.req.RechargeReq;
import cn.gnetop.dcs.server.service.logservice.rsp.RechargeRsp;
import cn.gnetop.dcs.system.listener.BeanUtils;
import cn.gnetop.pde.foundation.DateUtils;
import cn.gnetop.pde.foundation.encryte.MD5Utils;

public class RechargeHandler {

	private ServerInfoDao serverInfoDao = BeanUtils.getBean(ServerInfoDao.class);

	public RechargeRsp recharge(RechargeReq req) {
		Recharge recharge = new Recharge();
		recharge.setChannel(req.getChannel());
		recharge.setUserid(req.getUserid());
		recharge.setUsername(req.getUsername());
		recharge.setOrderid(req.getOrderid());
		recharge.setServerid(req.getServerid());
		recharge.setIp(req.getIp());
		recharge.setAmount(req.getAmount());
		recharge.setCreateTime(req.getCreateTime());
		recharge.setLogTime(DateUtils.getDateString());
		recharge.setMd5(MD5Utils.encrypt(req.toString()));

		ServerInfo info = new ServerInfo();
		info.setServerid(req.getServerid());
		info = serverInfoDao.findOne(info);
		if (null != info) {
			recharge.setGameid(info.getGameid());
		}

		RechargeDao dao = BeanUtils.getBean(RechargeDao.class);
		dao.insert(recharge);
		RechargeRsp rsp = new RechargeRsp();
		return rsp;
	}
}
