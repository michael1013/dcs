package cn.gnetop.dcs.console.service;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.gnetop.dcs.console.util.UserSession;
import cn.gnetop.dcs.dao.AdminPasswordDao;
import cn.gnetop.dcs.dao.AdminUserDao;
import cn.gnetop.dcs.dao.schema.AdminPassword;
import cn.gnetop.dcs.dao.schema.AdminUser;
import cn.gnetop.pde.foundation.DateUtils;
import cn.gnetop.pde.foundation.ValidateCode;
import cn.gnetop.pde.foundation.encryte.DESBaseUtils;
import cn.gnetop.pde.foundation.log.LogManager;

@Service
public class LoginService {

	private static final Logger logger = LogManager.getLogger();

	public static final String PWD_ENCRYPTE_KEY = "dcagNetop";

	@Autowired
	private AdminPasswordDao adminPasswordDao;
	@Autowired
	private AdminUserDao adminUserDao;

	public boolean loginValide(String username, String password, String ip) {
		if (StringUtils.isAnyBlank(username, password)) {
			return false;
		}
		String enPwd = password;
		try {
			enPwd = DESBaseUtils.encrypt(password, PWD_ENCRYPTE_KEY);
		} catch (Exception e) {
			e.printStackTrace();
		}
		AdminPassword pwd = new AdminPassword(username, enPwd);
		if (adminPasswordDao.findOne(pwd) != null) {
			AdminUser adminUser = new AdminUser(username);
			adminUser = adminUserDao.findOne(adminUser);
			UserSession.setUserSession(adminUser);
			AdminUser au = new AdminUser(username);
			au.setId(adminUser.getId());
			au.setLastLoginIp(ip);
			au.setLastLoginTime(DateUtils.getDateString());
			adminUserDao.update(au);
			return true;
		} else {
			return false;
		}
	}

	public boolean vcValide(HttpServletRequest request, String vc) {
		String vcCode = String.valueOf(request.getSession().getAttribute("vcCode"));
		if (vcCode.equalsIgnoreCase(vc)) {
			return true;
		} else {
			return false;
		}
	}

	public void writeVc(HttpServletRequest request, HttpServletResponse response) {
		// 设置响应的类型格式为图片格式
		response.setContentType("image/jpeg");
		// 禁止图像缓存。
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);

		HttpSession session = request.getSession();
		ValidateCode vCode = new ValidateCode(100, 32, 4, 50);
		session.setAttribute("vcCode", vCode.getCode());
		try {
			vCode.write(response.getOutputStream());
		} catch (IOException e) {
			logger.error("validecode create failed", e);
		}
	}
}
