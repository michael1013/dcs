package cn.gnetop.dcs.console.controller.log;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.gnetop.dcs.console.service.LogService;
import cn.gnetop.dcs.dao.base.Page;
import cn.gnetop.dcs.dao.schema.CoinConsume;
import cn.gnetop.dcs.dao.schema.CoinIncrease;
import cn.gnetop.dcs.dao.schema.GameInfo;
import cn.gnetop.dcs.dao.schema.PropertyConsume;
import cn.gnetop.dcs.dao.schema.PropertyIncrease;
import cn.gnetop.dcs.dao.schema.ServerInfo;

@Controller
@RequestMapping("/log/game")
public class LogGameController {

	@Autowired
	private LogService logService;

	/**
	 * 货币增加
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = { "", "/", "/coinIncreasePage" })
	public String coinIncreasePage(Model model) {
		List<GameInfo> gameList = logService.getGameList();
		model.addAttribute("gameList", gameList);
		List<ServerInfo> serverList = logService.getServerList();
		model.addAttribute("serverList", serverList);
		return "log/coinIncreasePage";
	}

	@RequestMapping("/coinIncreaseList")
	public String coinIncreaseList(Model model, HttpServletRequest request, CoinIncrease coinIncrease) {
		Page<CoinIncrease> page = logService.findCoinIncreasePage(coinIncrease);
		model.addAttribute("page", page);
		return "log/coinIncreaseList";
	}

	/**
	 * 
	 * 货币消耗
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = { "/coinConsumePage" })
	public String coinConsumePage(Model model) {
		List<GameInfo> gameList = logService.getGameList();
		model.addAttribute("gameList", gameList);
		List<ServerInfo> serverList = logService.getServerList();
		model.addAttribute("serverList", serverList);
		return "log/coinConsumePage";
	}

	@RequestMapping("/coinConsumeList")
	public String coinConsumeList(Model model, HttpServletRequest request, CoinConsume coinConsume) {
		Page<CoinConsume> page = logService.findCoinConsumePage(coinConsume);
		model.addAttribute("page", page);
		return "log/coinConsumeList";
	}

	/**
	 * 道具增加
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = { "/propIncreasePage" })
	public String propIncreasePage(Model model) {
		List<GameInfo> gameList = logService.getGameList();
		model.addAttribute("gameList", gameList);
		List<ServerInfo> serverList = logService.getServerList();
		model.addAttribute("serverList", serverList);
		return "log/propIncreasePage";
	}

	@RequestMapping("/propIncreaseList")
	public String propIncreaseList(Model model, HttpServletRequest request, PropertyIncrease propIncrease) {
		Page<PropertyIncrease> page = logService.findPropIncreasePage(propIncrease);
		model.addAttribute("page", page);
		return "log/propIncreaseList";
	}

	/**
	 * 道具消耗
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = { "/propConsumePage" })
	public String propConsumePage(Model model) {
		List<GameInfo> gameList = logService.getGameList();
		model.addAttribute("gameList", gameList);
		List<ServerInfo> serverList = logService.getServerList();
		model.addAttribute("serverList", serverList);
		return "log/propConsumePage";
	}

	@RequestMapping("/propConsumeList")
	public String propConsumeList(Model model, HttpServletRequest request, PropertyConsume propConsume) {
		Page<PropertyConsume> page = logService.findPropConsumePage(propConsume);
		model.addAttribute("page", page);
		return "log/propConsumeList";
	}

}
