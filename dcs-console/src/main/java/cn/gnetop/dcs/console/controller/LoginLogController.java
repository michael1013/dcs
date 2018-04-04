package cn.gnetop.dcs.console.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.gnetop.dcs.console.controller.base.BaseController;
import cn.gnetop.dcs.console.service.LoginLogService;
import cn.gnetop.dcs.dao.schema.LoginLog;

@Controller
@RequestMapping("/loginLog")
public class LoginLogController extends BaseController<LoginLog, LoginLogService> {

	@RequestMapping("/active")
	public String get(HttpServletRequest request, Model model, LoginLog l) {
		String name = this.getName(l);
		l = service.findOne(l);
		model.addAttribute(name, l);
		return name + "/active";
	}

}
