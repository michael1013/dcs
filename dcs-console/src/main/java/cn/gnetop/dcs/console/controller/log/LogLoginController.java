
package cn.gnetop.dcs.console.controller.log;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.gnetop.dcs.console.controller.base.BaseController;
import cn.gnetop.dcs.console.service.LogService;
import cn.gnetop.dcs.console.service.LoginLogService;
import cn.gnetop.dcs.dao.schema.GameInfo;
import cn.gnetop.dcs.dao.schema.LoginLog;
import cn.gnetop.dcs.dao.schema.ServerInfo;

@Controller
@RequestMapping("/log/login")
public class LogLoginController extends BaseController<LoginLog, LoginLogService> {

	@Autowired
	private LogService logService;

	@RequestMapping(value = { "", "/", "/page" })
	public String page(HttpServletRequest request, Model model, LoginLog entity) {
		List<GameInfo> gameList = logService.getGameList();
		model.addAttribute("gameList", gameList);
		List<ServerInfo> serverList = logService.getServerList();
		model.addAttribute("serverList", serverList);
		return "log/loginPage";
	}

}
