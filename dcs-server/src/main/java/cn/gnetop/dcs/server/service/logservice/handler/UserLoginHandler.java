package cn.gnetop.dcs.server.service.logservice.handler;

import cn.gnetop.dcs.dao.ServerInfoDao;
import cn.gnetop.dcs.dao.UserLoginDao;
import cn.gnetop.dcs.dao.schema.ServerInfo;
import cn.gnetop.dcs.dao.schema.UserLogin;
import cn.gnetop.dcs.server.service.logservice.req.UserLoginReq;
import cn.gnetop.dcs.server.service.logservice.rsp.UserLoginRsp;
import cn.gnetop.dcs.system.listener.BeanUtils;
import cn.gnetop.pde.foundation.DateUtils;
import cn.gnetop.pde.foundation.encryte.MD5Utils;

public class UserLoginHandler {

	private ServerInfoDao serverInfoDao = BeanUtils.getBean(ServerInfoDao.class);

	public UserLoginRsp userLogin(UserLoginReq req) {
		UserLogin u = new UserLogin();
		u.setUserid(req.getUserid());
		u.setUsername(req.getUsername());
		u.setNickname(req.getNickname());
		u.setIp(req.getIp());
		u.setServerid(req.getServerid());
		u.setCreateTime(req.getCreateTime());
		u.setLogTime(DateUtils.getDateString());
		u.setMd5(MD5Utils.encrypt(req.toString()));

		ServerInfo info = new ServerInfo();
		info.setServerid(req.getServerid());
		info = serverInfoDao.findOne(info);
		if (null != info) {
			u.setGameid(info.getGameid());
		}

		UserLoginDao dao = BeanUtils.getBean(UserLoginDao.class);
		dao.insert(u);

		UserLoginRsp rsp = new UserLoginRsp();
		return rsp;
	}
}
