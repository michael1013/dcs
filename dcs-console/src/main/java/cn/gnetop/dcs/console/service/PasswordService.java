package cn.gnetop.dcs.console.service;

import cn.gnetop.dcs.dao.PasswordDao;
import cn.gnetop.dcs.dao.schema.Password;
import cn.gnetop.pde.foundation.DateUtils;
import cn.gnetop.pde.foundation.encryte.DESBaseUtils;

public class PasswordService {

	private static final String PWD_ENCRYPTE_KEY = "dcsgNetop2017";

	private PasswordDao dao;

	public int update(Password t) {
		return this.modPwd(t, t.getPwd());
	}

	public int modPwd(Password p, String newPwd) {
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

		Password newPassword = new Password();
		newPassword.setUserid(p.getUserid());
		newPassword.setPwd(newPwdEncrypte);
		newPassword.setPast(pwdSb.toString());
		newPassword.setUpdateTime(DateUtils.getDateString());
		return dao.update(newPassword);
	}
}
