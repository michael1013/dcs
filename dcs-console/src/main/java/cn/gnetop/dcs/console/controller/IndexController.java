package cn.gnetop.dcs.console.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.gnetop.dcs.console.service.FileAnalyzeService;
import cn.gnetop.dcs.console.service.LoginService;
import cn.gnetop.dcs.console.util.UserSession;
import cn.gnetop.dcs.dao.schema.AdminUser;

@Controller
public class IndexController {

	@Autowired
	private LoginService loginService;

	@Autowired
	private FileAnalyzeService fileAnalyzeService;

	@RequestMapping(value = { "/", "" })
	public String execute() {
		return "redirect:/index";
	}

	@RequestMapping("/index")
	public String index(HttpServletRequest request, Model model) {
		AdminUser loginUser = UserSession.getLoginUser();
		model.addAttribute("user", loginUser);
		return "index";
	}
	
	@RequestMapping("/main")
	public String main(HttpServletRequest request, Model model) {
		AdminUser loginUser = UserSession.getLoginUser();
		model.addAttribute("user", loginUser);
		return "main";
	}

	@RequestMapping("/login")
	public String login(HttpServletRequest request) {
		if (UserSession.isLogin()) {
			return "redirect:/index";
		}
		return "login";
	}

	@RequestMapping("/logout")
	public String logout(HttpServletRequest request) {
		UserSession.logout();
		return "redirect:/login";
	}

	@ResponseBody
	@RequestMapping("/loginForm")
	public String loginForm(HttpServletRequest request, String username, String password, String vc) {
		if (loginService.vcValide(request, vc)) {
			if (loginService.loginValide(username, password, request.getRemoteAddr())) {
				return "index";
			} else {
				return "login";
			}
		} else {
			return "vc";
		}
	}

	@RequestMapping("vc")
	public void vc(HttpServletRequest request, HttpServletResponse response) {
		loginService.writeVc(request, response);
	}

	@ResponseBody
	@RequestMapping("vcValide")
	public boolean vcValide(HttpServletRequest request, String vc) {
		return loginService.vcValide(request, vc);
	}

	@ResponseBody
	@RequestMapping("run")
	public String run(Model model, String dateTime) {
		return fileAnalyzeService.doExecute(dateTime);
	}
}
