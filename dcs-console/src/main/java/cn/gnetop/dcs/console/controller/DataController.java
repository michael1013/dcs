package cn.gnetop.dcs.console.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.gnetop.dcs.console.service.DataService;
import cn.gnetop.dcs.console.service.LogService;
import cn.gnetop.dcs.dao.schema.GameInfo;
import cn.gnetop.dcs.dao.schema.ServerInfo;
import cn.gnetop.dcs.dao.schema.UserData;
import cn.gnetop.pde.foundation.DateUtils;

@Controller
@RequestMapping("/data")
public class DataController {

	@Autowired
	private LogService logService;

	@Autowired
	private DataService dataService;

	/**
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = { "", "/", "/userDataPage" })
	public String userDataPage(Model model) {
		List<GameInfo> gameList = logService.getGameList();
		List<ServerInfo> serverList = logService.getServerList();

		model.addAttribute("gameList", gameList);
		model.addAttribute("serverList", serverList);
		return "data/userDataPage";
	}

	@RequestMapping(value = { "/rechargeDataPage" })
	public String rechargeDataPage(Model model) {
		List<GameInfo> gameList = logService.getGameList();
		List<ServerInfo> serverList = logService.getServerList();

		model.addAttribute("gameList", gameList);
		model.addAttribute("serverList", serverList);
		return "data/rechargeDataPage";
	}

	@RequestMapping(value = { "/findStay" })
	public String findStay(Model model, ServerInfo server) {
		// 日期列表
		List<String> dateList = DateUtils.getDateListByDay(server.getBegin(), server.getEnd());
		model.addAttribute("dateList", dateList);
		Map<String, Map<String, Object>> dataMap = dataService.findStay(dateList, server);
		model.addAttribute("dataMap", dataMap);
		return "data/stayList";
	}

	@RequestMapping(value = { "/findActive" })
	public String findActive(Model model, ServerInfo server) {
		// 日期列表
		if (null != server) {
			List<String> dateList = DateUtils.getDateListByDay(server.getBegin(), server.getEnd());
			model.addAttribute("dateList", dateList);
			Map<String, Object> dataMap = dataService.findActive(server);
			model.addAttribute("dataMap", dataMap);
		}
		return "data/activeList";
	}

	@RequestMapping(value = { "/findNew" })
	public String findNew(Model model, ServerInfo server) {
		// 日期列表
		if (null != server) {
			List<String> dateList = DateUtils.getDateListByDay(server.getBegin(), server.getEnd());
			model.addAttribute("dateList", dateList);
			Map<String, Object> dataMap = dataService.findNew(server);
			model.addAttribute("dataMap", dataMap);
		}
		return "data/newList";
	}

	@RequestMapping(value = { "/findArpuList" })
	public String findArpuList(Model model, ServerInfo server, String begin, String end) {
		// 日期列表
		List<String> dateList = DateUtils.getDateListByDay(begin, end);
		model.addAttribute("dateList", dateList);
		// dataService.findDataList(dateList);
		return "data/arpuList";
	}
	
	@RequestMapping(value = { "/kpiPage" })
	public String kpiPage(Model model, ServerInfo server) {
		List<GameInfo> gameList = logService.getGameList();
		List<ServerInfo> serverList = logService.getServerList();

		model.addAttribute("gameList", gameList);
		model.addAttribute("serverList", serverList);
		return "data/kpiPage";
	}

	@RequestMapping(value = { "/findKpi" })
	public String findKpi(Model model, ServerInfo server) {
		// 日期列表
		List<String> dateList = DateUtils.getDateListByDay(server.getBegin(), server.getEnd());
		model.addAttribute("dateList", dateList);
		Map<String, UserData> dataMap = dataService.findKpi(server, dateList);
		model.addAttribute("dataMap", dataMap);
		return "data/kpiList";
	}
	
	@RequestMapping(value = { "/findMonthKpi" })
	public String findMonthKpi(Model model, ServerInfo server) {
		// 日期列表
		List<String> dateList = Arrays.asList(server.getBegin());
		model.addAttribute("dateList", dateList);
		Map<String, UserData> dataMap = dataService.findMonthKpi(server, dateList);
		model.addAttribute("dataMap", dataMap);
		return "data/kpiMonthList";
	}

	@RequestMapping(value = { "/findRechargeDataList" })
	public String findRechargeDataList(Model model, ServerInfo server, String begin, String end) {
		// 日期列表
		List<String> dateList = DateUtils.getDateListByDay(begin, end);
		model.addAttribute("dateList", dateList);
		Map<String, Map<String, Object>> dataMap = dataService.findRechargeData(server, dateList);
		model.addAttribute("dataMap", dataMap);
		return "data/rechargeDataList";
	}
}
