package cn.gnetop.dcs.portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.gnetop.dcs.dao.schema.User;
import cn.gnetop.dcs.portal.service.UserService;
import cn.gnetop.pde.foundation.JsonUtils;
import cn.gnetop.pde.foundation.StringUtils;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@ResponseBody
	@RequestMapping("/login")
	public String login(String username, String password) {
		if (StringUtils.isNotBlank(username, password)) {
			User user = userService.login(username, password);
			if (null != user) {
				return JsonUtils.toString(user);
			}
		}
		return "{}";
	}
	
	@ResponseBody
	@RequestMapping("/regist")
	public String regist(User user, String password){
		if (null != user && StringUtils.isNotBlank(user.getUsername(), password)) {
			String result = userService.regist(user, password);
			return result;
		}
		return "param error";
	}

}
