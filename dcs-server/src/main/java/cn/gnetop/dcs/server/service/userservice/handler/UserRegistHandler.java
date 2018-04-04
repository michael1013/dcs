package cn.gnetop.dcs.server.service.userservice.handler;

import java.util.Random;
import java.util.UUID;

import cn.gnetop.dcs.dao.PasswordDao;
import cn.gnetop.dcs.dao.UserDao;
import cn.gnetop.dcs.dao.schema.Password;
import cn.gnetop.dcs.dao.schema.User;
import cn.gnetop.dcs.server.convertor.UserConvertor;
import cn.gnetop.dcs.server.service.userservice.req.UserRegistReq;
import cn.gnetop.dcs.server.service.userservice.rsp.UserRegistRsp;
import cn.gnetop.dcs.system.entity.ResultCode;
import cn.gnetop.dcs.system.exception.DcsException;
import cn.gnetop.dcs.system.exception.ParamErrException;
import cn.gnetop.dcs.system.listener.BeanUtils;
import cn.gnetop.pde.foundation.DateUtils;
import cn.gnetop.pde.foundation.StringUtils;
import cn.gnetop.pde.foundation.encryte.DESBaseUtils;

public class UserRegistHandler {

	private static final char[] RANDOM_WORD_POOL = { 'q', 'w', 'e', 'r', 't', 'y', 'u', 'i', 'o', 'p', 'a', 's', 'd',
	        'f', 'g', 'h', 'j', 'k', 'l', 'z', 'x', 'c', 'v', 'b', 'n', 'm' };
	private static final char[] RANDOM_PWD_POOL = { 'q', 'w', 'e', 'r', 't', 'y', 'u', 'i', 'o', 'p', 'a', 's', 'd',
	        'f', 'g', 'h', 'j', 'k', 'l', 'z', 'x', 'c', 'v', 'b', 'n', 'm', 'Q', 'W', 'E', 'R', 'T', 'Y', 'U', 'I',
	        'O', 'P', 'A', 'S', 'D', 'F', 'G', 'H', 'J', 'K', 'L', 'Z', 'X', 'C', 'V', 'B', 'N', 'M', '0', '1', '2',
	        '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3',
	        '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4',
	        '5', '6', '7', '8', '9' };
	private static final String REGISTTYPE_ACCOUNT = "account";
	private static final String REGISTTYPE_RANDOM = "random";
	private static final String REGISTTYPE_PHONE = "phone";

	public static final String PWD_ENCRYPTE_KEY = "dcsgNetop2017";

	public UserRegistRsp userRegist(UserRegistReq req) throws DcsException {
		String username = req.getUsername();
		String password = req.getPassword();
		String mobile = req.getMobile();
		UserDao userDao = BeanUtils.getBean(UserDao.class);
		User user = new User();

		String registType = StringUtils.isBlank(req.getRegistType()) ? "" : req.getRegistType();
		// 随机账号及密码
		switch (registType) {
		case REGISTTYPE_PHONE:
			if (StringUtils.isBlank(mobile)) {
				throw new ParamErrException(ResultCode.PHONENUM_ERROR);
			}
			user.setMobile(mobile);
			user = userDao.findOne(user);
			if (null != user) {
				throw new ParamErrException(ResultCode.MOBILE_ALREADY_BIND);
			} else {
				user = new User();
			}
			user.setMobile(mobile);
			user.setIsGuest(Boolean.FALSE);
		case REGISTTYPE_RANDOM:
			if (null == user.getIsGuest()) {
				user.setIsGuest(Boolean.TRUE);
			}
			Random r = new Random();
			User u = null;
			String nameRandom = null;
			int count = 0;
			do {
				if (count > 100) {
					// 重试次数过多抛出异常
					throw new DcsException(ResultCode.REGIST_ERR);
				}
				StringBuffer nameSb = new StringBuffer();
				for (int i = 0; i < 4; i++) {
					nameSb.append(RANDOM_WORD_POOL[r.nextInt(RANDOM_WORD_POOL.length)]);
				}
				int num = r.nextInt(1000000) + 1000000;
				nameSb.append(String.valueOf(num).substring(1));
				nameRandom = nameSb.toString();
				u = new User();
				u.setUsername(nameSb.toString());
				u = userDao.findOne(u);
				count++;
			} while (null != u);
			username = nameRandom;
			StringBuffer pwdSb = new StringBuffer();
			for (int i = 0; i < 8; i++) {
				pwdSb.append(RANDOM_PWD_POOL[r.nextInt(RANDOM_PWD_POOL.length)]);
			}
			password = pwdSb.toString();
			user.setPassword(password);
			break;
		default:
			if (!username.matches("^[a-zA-Z][A-Za-z0-9._]+")) {
				throw new ParamErrException(ResultCode.USERNAME_MUST_STARTS_BY_LETTER);
			}
			user.setUsername(username);
			user = userDao.findOne(user);
			if (null != user) {
				throw new ParamErrException(ResultCode.USER_ALREADY_EXISTS);
			} else {
				user = new User();
			}
			break;
		}

		if (StringUtils.isBlank(username)) {
			throw new ParamErrException(ResultCode.PARAM_ERR);
		} else {
			user.setUsername(username);
		}

		if (StringUtils.isBlank(password)) {
			throw new ParamErrException(ResultCode.PASSWORD_ERROR);
		}

		String now = DateUtils.getDateString();

		user.setDeviceToken(req.getDeviceToken());
		user.setToken(UUID.randomUUID().toString());
		user.setTokenDate(now);
		user.setLat(req.getLat());
		user.setLng(req.getLng());
		user.setLastLoginTime(now);
		user.setCreateTime(now);
		int result = userDao.insert(user);
		if (result != 1) {
			throw new DcsException(ResultCode.SYS_ERR);
		}

		PasswordDao pwdDao = BeanUtils.getBean(PasswordDao.class);
		Password p = new Password();
		p.setUserid(user.getUserid());
		String pwd = password;
		try {
			pwd = DESBaseUtils.encrypt(password, PWD_ENCRYPTE_KEY);
		} catch (Exception e) {
			throw new DcsException();
		}
		p.setPwd(pwd);
		p.setCreateTime(now);
		int resultPwd = pwdDao.insert(p);
		if (1 != resultPwd) {
			throw new DcsException(ResultCode.SYS_ERR);
		}

		User u = new User();
		u.setUsername(user.getUsername());
		u = userDao.findOne(u);
		if (null == u) {
			throw new DcsException();
		}

		UserRegistRsp rsp = new UserRegistRsp();
		rsp.putData("user", UserConvertor.convertor(user));
		return rsp;
	}

}
