package cn.gnetop.dcs.console.controller.log;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.gnetop.dcs.console.service.LogService;
import cn.gnetop.dcs.dao.base.Page;
import cn.gnetop.dcs.dao.schema.GameInfo;
import cn.gnetop.dcs.dao.schema.Recharge;
import cn.gnetop.dcs.dao.schema.RechargeRank;
import cn.gnetop.dcs.dao.schema.ServerInfo;

@Controller
@RequestMapping("/log/recharge")
public class LogRechargeController {

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
	public String rechargePage(Model model) {
		List<GameInfo> gameList = logService.getGameList();
		model.addAttribute("gameList", gameList);
		List<ServerInfo> serverList = logService.getServerList();
		model.addAttribute("serverList", serverList);
		return "log/rechargePage";
	}

	@RequestMapping(value = { "/totalList" })
	public String totalList(Model model, HttpServletRequest request, Recharge recharge) {
		Page<Recharge> page = logService.findRechargePage(recharge);
		model.addAttribute("page", page);
		return "log/rechargeList";
	}

	@RequestMapping("/list")
	public String list(Model model, HttpServletRequest request, Recharge recharge) {
		Page<Recharge> page = logService.findRechargePage(recharge);
		model.addAttribute("page", page);
		return "log/rechargeList";
	}

	@RequestMapping("/rankPage")
	public String rankPage(Model model) {
		List<GameInfo> gameList = logService.getGameList();
		model.addAttribute("gameList", gameList);
		List<ServerInfo> serverList = logService.getServerList();
		model.addAttribute("serverList", serverList);
		return "log/rechargeRankPage";
	}

	@RequestMapping("/rankList")
	public String rankList(Model model, Recharge recharge) {
		Page<RechargeRank> page = logService.findRank(recharge);
		model.addAttribute("page", page);
		return "log/rechargeRankList";
	}

}
