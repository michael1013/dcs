package cn.gnetop.dcs.portal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.gnetop.dcs.dao.PasswordDao;
import cn.gnetop.dcs.dao.UserDao;
import cn.gnetop.dcs.dao.schema.Password;
import cn.gnetop.dcs.dao.schema.User;
import cn.gnetop.pde.foundation.DateUtils;
import cn.gnetop.pde.foundation.StringUtils;
import cn.gnetop.pde.foundation.encryte.DESBaseUtils;

@Service
public class UserService {

	public static final String PWD_ENCRYPTE_KEY = "dcsgNetop2017";

	@Autowired
	private UserDao userDao;

	@Autowired
	private PasswordDao passwordDao;

	public User login(String username, String password) {
		if (StringUtils.isNotBlank(username, password)) {
			User user = new User();
			user.setUsername(username);
			user = userDao.findOne(user);
			if (null != user) {
				String pwdEncode = password;
				try {
					pwdEncode = DESBaseUtils.encrypt(password, PWD_ENCRYPTE_KEY);
				} catch (Exception e) {
					e.printStackTrace();
				}
				Password p = new Password();
				p.setUserid(user.getUserid());
				p.setPwd(pwdEncode);
				p = passwordDao.findOne(p);
				if (null != p) {
					return user;
				}
			}
		}
		return null;
	}

	public String regist(User user, String password) {
		if (StringUtils.isNotBlank(user.getUsername(), password)) {
			User u = userDao.findOne(user);
			if (null != u) {
				return "exists";
			}
			user.setCreateTime(DateUtils.getDateString());
			userDao.insert(user);
			
			String pwdEncode = password;
			try {
				pwdEncode = DESBaseUtils.encrypt(password, PWD_ENCRYPTE_KEY);
			} catch (Exception e) {
				e.printStackTrace();
			}
			Password p = new Password();
			p.setUserid(user.getUserid());
			p.setPwd(pwdEncode);
			p.setCreateTime(DateUtils.getDateString());
			passwordDao.insert(p);
			return "success";
		}
		return "param error";
	}
}
