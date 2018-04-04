package cn.gnetop.dcs.console.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.gnetop.dcs.console.service.LogService;
import cn.gnetop.dcs.dao.base.Page;
import cn.gnetop.dcs.dao.schema.GameInfo;
import cn.gnetop.dcs.dao.schema.ServerInfo;

@Controller
@RequestMapping("/server")
public class ServerController {

	@Autowired
	private LogService logService;

	/**
	 * 货币增加
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = { "", "/", "/page" })
	public String page(Model model) {
		List<GameInfo> gameList = logService.getGameList();
		model.addAttribute("gameList", gameList);
		return "server/serverPage";
	}

	@RequestMapping("/list")
	public String list(Model model, HttpServletRequest request, ServerInfo info) {
		Page<ServerInfo> page = logService.getServerPage(info);
		model.addAttribute("page", page);
		return "server/serverList";
	}

	@ResponseBody
	@RequestMapping("/add")
	public String add(Model model, ServerInfo info) {
		// TODO 
		if (null != info && null == info.getId()) {
			ServerInfo i = logService.findServerInfo(info.getServerid());
			if (null != i) {
				return "repeat";
			}
		}
		int result = logService.addServer(info);
		if (1 != result) {
			return "error";
		}
		return "success";
	}

}
