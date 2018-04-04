package cn.gnetop.dcs.server.service.userservice.handler;

import cn.gnetop.dcs.dao.PasswordDao;
import cn.gnetop.dcs.dao.UserDao;
import cn.gnetop.dcs.dao.schema.Password;
import cn.gnetop.dcs.dao.schema.User;
import cn.gnetop.dcs.server.helper.TokenManager;
import cn.gnetop.dcs.server.service.userservice.req.UpdatePasswordReq;
import cn.gnetop.dcs.server.service.userservice.rsp.UpdatePasswordRsp;
import cn.gnetop.dcs.system.entity.ResultCode;
import cn.gnetop.dcs.system.exception.DcsException;
import cn.gnetop.dcs.system.exception.ParamErrException;
import cn.gnetop.dcs.system.listener.BeanUtils;
import cn.gnetop.pde.foundation.DateUtils;
import cn.gnetop.pde.foundation.StringUtils;
import cn.gnetop.pde.foundation.encryte.DESBaseUtils;

public class UpdatePasswordHandler {

	private PasswordDao pwdDao = BeanUtils.getBean(PasswordDao.class);

	public UpdatePasswordRsp updatePassword(UpdatePasswordReq req) throws DcsException {
		String userid = req.getUserid();
		String username = req.getUsername();
		if (StringUtils.isAllBlank(userid, username)) {
			throw new ParamErrException(ResultCode.PARAM_ERR);
		}
		User user = new User();
		user.setUserid(userid);
		user.setUsername(username);
		UserDao dao = BeanUtils.getBean(UserDao.class);
		user = dao.findOne(user);
		if (null == user) {
			throw new ParamErrException(ResultCode.USER_NOT_EXISTS);
		}

		if (!TokenManager.verifyToken(req.getToken(), user.getToken(), user.getTokenDate())) {
			throw new ParamErrException(ResultCode.TOKEN_INVALID);
		}

		userid = user.getUserid();
		String oldPwd = req.getOldPwd();
		String newPwd = req.getNewPwd();
		if (StringUtils.isBlank(newPwd)) {
			throw new ParamErrException(ResultCode.PASSWORD_ERROR);
		}
		String oldPwdEncrypte = oldPwd;
		try {
			oldPwdEncrypte = DESBaseUtils.encrypt(oldPwd, UserRegistHandler.PWD_ENCRYPTE_KEY);
		} catch (Exception e) {
			e.printStackTrace();
		}

		String now = DateUtils.getDateString();

		Password p = new Password();
		p.setUserid(userid);
		p = pwdDao.findOne(p);
		if (null == p) {
			p = new Password();
			p.setUserid(userid);
			p.setPwd(oldPwdEncrypte);
			p.setCreateTime(now);
			pwdDao.insert(p);
		}

		if (!oldPwdEncrypte.equals(p.getPwd())) {
			throw new ParamErrException();
		}

		modPwd(p, newPwd);

		UpdatePasswordRsp rsp = new UpdatePasswordRsp();
		return rsp;
	}

	public static void modPwd(Password p, String newPwd) throws DcsException {
		String newPwdEncrypte = newPwd;
		try {
			newPwdEncrypte = DESBaseUtils.encrypt(newPwd, UserRegistHandler.PWD_ENCRYPTE_KEY);
		} catch (Exception e) {
			throw new DcsException();
		}
		String past = null == p.getPast() ? "" : p.getPast();
		String[] pasts = past.split("\\|");
		StringBuffer pwdSb = new StringBuffer(p.getPwd());
		if (pasts.length > 3) {
			pwdSb.append('|').append(pasts[0]);
			pwdSb.append('|').append(pasts[1]);
			pwdSb.append('|').append(pasts[2]);
		} else {
			pwdSb.append('|').append(past);
		}

		PasswordDao dao = BeanUtils.getBean(PasswordDao.class);
		Password newPassword = new Password();
		newPassword.setUserid(p.getUserid());
		newPassword.setPwd(newPwdEncrypte);
		newPassword.setPast(pwdSb.toString());
		newPassword.setUpdateTime(DateUtils.getDateString());
		int pwdResult = dao.update(newPassword);
		if (1 != pwdResult) {
			throw new DcsException(ResultCode.SYS_ERR);
		}
	}
}
