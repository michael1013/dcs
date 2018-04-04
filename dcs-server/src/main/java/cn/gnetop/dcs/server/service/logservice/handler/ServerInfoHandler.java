package cn.gnetop.dcs.server.service.logservice.handler;

import cn.gnetop.dcs.dao.ServerInfoDao;
import cn.gnetop.dcs.dao.schema.ServerInfo;
import cn.gnetop.dcs.server.service.logservice.req.ServerInfoReq;
import cn.gnetop.dcs.server.service.logservice.rsp.ServerInfoRsp;
import cn.gnetop.dcs.system.listener.BeanUtils;
import cn.gnetop.pde.foundation.DateUtils;

public class ServerInfoHandler {
	public ServerInfoRsp serverInfo(ServerInfoReq req) {
		ServerInfo info = new ServerInfo();
		info.setServerid(req.getNewserverid());
		info.setServerName(req.getServerName());
		info.setOpenTime(req.getOpenTime());
		info.setCloseTime(req.getCloseTime());
		info.setIntegrateTime(req.getIntegrateTime());
		info.setCreateTime(DateUtils.getDateString());

		ServerInfoDao dao = BeanUtils.getBean(ServerInfoDao.class);
		dao.insert(info);

		ServerInfoRsp rsp = new ServerInfoRsp();
		return rsp;
	}
}
