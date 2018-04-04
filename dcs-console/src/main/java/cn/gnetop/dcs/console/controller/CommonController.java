package cn.gnetop.dcs.console.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.gnetop.dcs.console.service.LogService;
import cn.gnetop.dcs.dao.schema.GameInfo;
import cn.gnetop.dcs.dao.schema.ServerInfo;

@Controller
@RequestMapping("/common")
public class CommonController {
	
	@Autowired
	private LogService logService;

	@RequestMapping(value = { "/filter" })
	public String filter(Model model, ServerInfo server) {
		List<GameInfo> gameList = logService.getGameList();
		model.addAttribute("gameList", gameList);
		List<ServerInfo> serverList = logService.getServerList();
		model.addAttribute("serverList", serverList);
		return "common/filter";
	}
	
}
