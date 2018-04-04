package cn.gnetop.dcs.console.service.impl;

import org.springframework.stereotype.Service;

import cn.gnetop.dcs.console.service.UserPasswordService;
import cn.gnetop.dcs.console.service.base.BaseServiceImpl;
import cn.gnetop.dcs.dao.mapper.UserPasswordMapperDaoImpl;
import cn.gnetop.dcs.dao.schema.UserPassword;
import cn.gnetop.pde.foundation.DateUtils;
import cn.gnetop.pde.foundation.encryte.DESBaseUtils;

@Service
public class UserPasswordServiceImpl extends BaseServiceImpl<UserPassword, UserPasswordMapperDaoImpl>
        implements UserPasswordService {

	private static final String PWD_ENCRYPTE_KEY = "dcsgNetop2017";

	@Override
	public int update(UserPassword t) {
		return this.modPwd(t, t.getPwd());
	}

	public int modPwd(UserPassword p, String newPwd) {
		String newPwdEncrypte = newPwd;
		try {
			newPwdEncrypte = DESBaseUtils.encrypt(newPwd, PWD_ENCRYPTE_KEY);
		} catch (Exception e) {
			throw new RuntimeException();
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

		UserPassword newPassword = new UserPassword();
		newPassword.setId(p.getId());
		newPassword.setUserid(p.getUserid());
		newPassword.setPwd(newPwdEncrypte);
		newPassword.setPast(pwdSb.toString());
		newPassword.setUpdateTime(DateUtils.getDateString());
		return dao.update(newPassword);
	}
}
