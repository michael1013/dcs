package cn.gnetop.dcs.server.service.userservice.handler;

import cn.gnetop.dcs.dao.UserDao;
import cn.gnetop.dcs.dao.schema.User;
import cn.gnetop.dcs.server.convertor.UserConvertor;
import cn.gnetop.dcs.server.helper.TokenManager;
import cn.gnetop.dcs.server.service.userservice.req.UpdateUserInfoReq;
import cn.gnetop.dcs.server.service.userservice.rsp.UpdateUserInfoRsp;
import cn.gnetop.dcs.system.entity.ResultCode;
import cn.gnetop.dcs.system.exception.DcsException;
import cn.gnetop.dcs.system.exception.ParamErrException;
import cn.gnetop.dcs.system.listener.BeanUtils;
import cn.gnetop.pde.foundation.StringUtils;

public class UpdateUserInfoHandler {

	public UpdateUserInfoRsp updateUserInfo(UpdateUserInfoReq req) throws DcsException {
		String userid = req.getUserid();
		String username = req.getUsername();
		if (StringUtils.isAllBlank(userid, username)) {
			throw new ParamErrException(ResultCode.PARAM_ERR);
		}
		UserDao dao = BeanUtils.getBean(UserDao.class);
		User u = new User();
		u.setUserid(userid);
		u.setUsername(username);
		u = dao.findOne(u);
		if (null == u) {
			throw new ParamErrException(ResultCode.USER_NOT_EXISTS);
		}

		if (!TokenManager.verifyToken(req.getToken(), u.getToken(), u.getTokenDate())) {
			throw new ParamErrException(ResultCode.TOKEN_INVALID);
		}

		userid = u.getUserid();
		username = u.getUsername();

		User user = new User();
		user.setUserid(userid);
		user.setUsername(username);
		user.setNickname(req.getNickname());
		int result = dao.update(user);
		if (1 != result) {
			throw new DcsException(ResultCode.SYS_ERR);
		}
		u = new User();
		u.setUserid(userid);
		u.setUsername(username);
		u = dao.findOne(u);
		UpdateUserInfoRsp rsp = new UpdateUserInfoRsp();
		rsp.putData("user", UserConvertor.convertor(u));
		return rsp;
	}
}
