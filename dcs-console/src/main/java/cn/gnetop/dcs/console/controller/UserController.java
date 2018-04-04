package cn.gnetop.dcs.console.controller;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.gnetop.dcs.console.controller.base.BaseController;
import cn.gnetop.dcs.console.service.UserService;
import cn.gnetop.dcs.dao.schema.User;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController<User, UserService> {

	@RequestMapping("/data")
	public String data(Model model, User user) {
		return this.getName(user) + "/data";
	}

	@RequestMapping("/new")
	public String findNew(Model model, User user) {
		List<Map<String, String>> listMap = service.findNew(user);
		model.addAttribute("listMap", listMap);
		return this.getName(user) + "/new";
	}

}
