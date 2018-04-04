package cn.gnetop.dcs.server.service.userservice.handler;


import cn.gnetop.dcs.dao.UserDao;
import cn.gnetop.dcs.dao.schema.User;
import cn.gnetop.dcs.server.convertor.UserConvertor;
import cn.gnetop.dcs.server.helper.TokenManager;
import cn.gnetop.dcs.server.service.userservice.req.BindMobileReq;
import cn.gnetop.dcs.server.service.userservice.rsp.BindMobileRsp;
import cn.gnetop.dcs.system.entity.ResultCode;
import cn.gnetop.dcs.system.exception.DcsException;
import cn.gnetop.dcs.system.exception.ParamErrException;
import cn.gnetop.dcs.system.listener.BeanUtils;
import cn.gnetop.pde.foundation.DateUtils;
import cn.gnetop.pde.foundation.StringUtils;

public class BindMobileHandler {
	
	private UserDao userDao = BeanUtils.getBean(UserDao.class);

	public BindMobileRsp bindMobile(BindMobileReq req) throws DcsException {
		String userid = req.getUserid();
		String username = req.getUsername();
		String mobile = req.getMobile();
		if (StringUtils.isAllBlank(userid, username)) {
			throw new ParamErrException(ResultCode.USER_NOT_EXISTS);
		}
		if (StringUtils.isBlank(mobile)) {
			throw new ParamErrException(ResultCode.PHONENUM_ERROR);
		}
		
		// 校验用户是否存在
		User user = new User();
		user.setUserid(userid);
		user.setUsername(username);
		user = userDao.findOne(user);
		if (null == user) {
			throw new ParamErrException(ResultCode.USER_NOT_EXISTS);
		}
		
		// 校验token是否已过期
		if (!TokenManager.verifyToken(req.getToken(), user.getToken(), user.getTokenDate())) {
			throw new ParamErrException(ResultCode.TOKEN_INVALID);
		}
		
		// 校验手机号是否已绑定其他账号
		User uMobile = new User();
		uMobile.setMobile(mobile);
		uMobile = userDao.findOne(uMobile);
		if (null != uMobile) {
			throw new ParamErrException(ResultCode.MOBILE_ALREADY_BIND);
		}
		
		// 修改手机号
		User u = new User();
		u.setUserid(user.getUserid());
		u.setMobile(mobile);
		// 绑定手机的账号未非游客用户
		u.setIsGuest(Boolean.FALSE);
		u.setUpdateTime(DateUtils.getDateString());
		int result = userDao.update(u);
		if (1 != result) {
			throw new DcsException();
		}
		u = new User();
		u.setUserid(user.getUserid());
		u = userDao.findOne(u);
		if (null == u) {
			throw new DcsException("user is null");
		}
		BindMobileRsp rsp = new BindMobileRsp();
		rsp.putData("user", UserConvertor.convertor(u));
		return rsp;
	}
}
