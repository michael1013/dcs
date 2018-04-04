package cn.gnetop.dcs.server.service.userservice.handler;

import java.util.UUID;

import cn.gnetop.dcs.dao.LoginLogDao;
import cn.gnetop.dcs.dao.PasswordDao;
import cn.gnetop.dcs.dao.UserDao;
import cn.gnetop.dcs.dao.schema.LoginLog;
import cn.gnetop.dcs.dao.schema.Password;
import cn.gnetop.dcs.dao.schema.User;
import cn.gnetop.dcs.server.convertor.UserConvertor;
import cn.gnetop.dcs.server.service.userservice.req.UserLoginReq;
import cn.gnetop.dcs.server.service.userservice.rsp.UserLoginRsp;
import cn.gnetop.dcs.system.engine.Engine;
import cn.gnetop.dcs.system.entity.ResultCode;
import cn.gnetop.dcs.system.exception.DcsException;
import cn.gnetop.dcs.system.exception.ParamErrException;
import cn.gnetop.dcs.system.listener.BeanUtils;
import cn.gnetop.pde.foundation.DateUtils;
import cn.gnetop.pde.foundation.StringUtils;
import cn.gnetop.pde.foundation.encryte.DESBaseUtils;

public class UserLoginHandler {

	public UserLoginRsp userLogin(UserLoginReq req) throws DcsException {
		String username = req.getUsername();
		String password = req.getPassword();
		String mobile = req.getMobile();

		String now = DateUtils.getDateString();

		UserDao userDao = BeanUtils.getBean(UserDao.class);
		User user = new User();
		if (StringUtils.isNoneBlank(username)) {
			if (StringUtils.isBlank(password)) {
				throw new ParamErrException(ResultCode.PASSWORD_ERROR);
			}

			user.setUsername(username);
			user.setPassword(password);
			String encryptPwd = "";
			try {
				encryptPwd = DESBaseUtils.encrypt(password, UserRegistHandler.PWD_ENCRYPTE_KEY);
			} catch (Exception e) {
				e.printStackTrace();
			}

			user = userDao.findOne(user);
			if (null == user && username.matches("\\d+")) {
				user = new User();
				user.setMobile(username);
				user = userDao.findOne(user);
			}

			if (null == user) {
				throw new ParamErrException(ResultCode.USER_NOT_EXISTS);
			}

			PasswordDao pwdDao = BeanUtils.getBean(PasswordDao.class);
			Password pwd = new Password();
			pwd.setUserid(user.getUserid());
			pwd.setPwd(encryptPwd);
			pwd = pwdDao.findOne(pwd);
			if (null == pwd || !encryptPwd.equals(pwd.getPwd())) {
				throw new ParamErrException(ResultCode.PASSWORD_WRONG);
			}
		} else if (StringUtils.isNotBlank(mobile)) {
			user.setMobile(mobile);
		} else {
			throw new ParamErrException(ResultCode.USERNAME_ERROR);
		}

		user = userDao.findOne(user);
		if (null == user) {
			throw new ParamErrException(ResultCode.USER_NOT_EXISTS);
		}

		String token = UUID.randomUUID().toString();

		User u = new User();
		u.setUserid(user.getUserid());
		u.setLastLoginTime(now);
		u.setToken(token);
		u.setTokenDate(DateUtils.getDateString());
		userDao.update(u);

		user.setToken(token);

		final User f = user;

		new Runnable() {
			@Override
			public void run() {
				LoginLogDao loginDao = BeanUtils.getBean(LoginLogDao.class);
				LoginLog l = new LoginLog();
				l.setUserid(f.getUserid());
				l.setUsername(f.getUsername());
				l.setMobile(f.getMobile());
				l.setDevice(f.getDeviceToken());
				l.setToken(f.getToken());
				if (null != Engine.getThreadMessageContext()) {
					l.setIp(Engine.getThreadMessageContext().getIp());
				}
				l.setCreateTime(DateUtils.getDateString());
				loginDao.insert(l);

			}
		}.run();

		UserLoginRsp rsp = new UserLoginRsp();
		rsp.putData("user", UserConvertor.convertor(user));
		return rsp;
	}
}
