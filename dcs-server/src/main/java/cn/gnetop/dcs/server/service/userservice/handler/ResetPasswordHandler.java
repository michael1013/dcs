package cn.gnetop.dcs.server.service.userservice.handler;

import cn.gnetop.dcs.dao.PasswordDao;
import cn.gnetop.dcs.dao.UserDao;
import cn.gnetop.dcs.dao.schema.Password;
import cn.gnetop.dcs.dao.schema.User;
import cn.gnetop.dcs.server.service.userservice.req.ResetPasswordReq;
import cn.gnetop.dcs.server.service.userservice.rsp.ResetPasswordRsp;
import cn.gnetop.dcs.system.entity.ResultCode;
import cn.gnetop.dcs.system.exception.DcsException;
import cn.gnetop.dcs.system.exception.ParamErrException;
import cn.gnetop.dcs.system.listener.BeanUtils;
import cn.gnetop.pde.foundation.StringUtils;

public class ResetPasswordHandler {

	private UserDao userDao = BeanUtils.getBean(UserDao.class);

	private PasswordDao pwdDao = BeanUtils.getBean(PasswordDao.class);

	public ResetPasswordRsp resetPassword(ResetPasswordReq req) throws DcsException {
		String mobile = req.getMobile();
		String pwd = req.getNewPwd();
		if (StringUtils.hasBlank(mobile, pwd)) {
			throw new ParamErrException(ResultCode.PARAM_ERR);
		}
		User user = new User();
		user.setMobile(mobile);
		user = userDao.findOne(user);
		if (null == user) {
			throw new ParamErrException(ResultCode.USER_NOT_EXISTS);
		}
		Password p = new Password();
		p.setUserid(user.getUserid());
		p = pwdDao.findOne(p);

		UpdatePasswordHandler.modPwd(p, pwd);

		ResetPasswordRsp rsp = new ResetPasswordRsp();
		return rsp;
	}
}
