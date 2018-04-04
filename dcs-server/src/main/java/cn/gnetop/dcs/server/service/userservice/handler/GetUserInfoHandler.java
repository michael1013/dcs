package cn.gnetop.dcs.server.service.userservice.handler;

import cn.gnetop.dcs.dao.UserDao;
import cn.gnetop.dcs.dao.schema.User;
import cn.gnetop.dcs.server.convertor.UserConvertor;
import cn.gnetop.dcs.server.service.userservice.req.GetUserInfoReq;
import cn.gnetop.dcs.server.service.userservice.rsp.GetUserInfoRsp;
import cn.gnetop.dcs.system.entity.ResultCode;
import cn.gnetop.dcs.system.exception.DcsException;
import cn.gnetop.dcs.system.exception.ParamErrException;
import cn.gnetop.dcs.system.listener.BeanUtils;
import cn.gnetop.pde.foundation.StringUtils;

public class GetUserInfoHandler {
	public GetUserInfoRsp getUserInfo(GetUserInfoReq req) throws DcsException {
		String userid = req.getUserid();
		String username = req.getUsername();
		String mobile = req.getMobile();
		User user = new User();
		if (StringUtils.hasNotBlank(userid, username, mobile)) {
			UserDao dao = BeanUtils.getBean(UserDao.class);
			user.setUserid(userid);
			user.setUsername(username);
			user.setMobile(mobile);
			user = dao.findOne(user);
			if (null == user) {
				throw new ParamErrException(ResultCode.USER_NOT_EXISTS);
			}
		}
		GetUserInfoRsp rsp = new GetUserInfoRsp();
		rsp.putData("user", UserConvertor.convertor(user));
		return rsp;
	}
}
