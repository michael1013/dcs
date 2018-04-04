
package cn.gnetop.dcs.console.controller;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.gnetop.dcs.console.service.FileAnalyzeService;
import cn.gnetop.dcs.console.service.LogService;
import cn.gnetop.dcs.console.service.OptionService;
import cn.gnetop.dcs.console.service.helper.FileAnalyzeThread;
import cn.gnetop.dcs.dao.base.Page;
import cn.gnetop.dcs.dao.schema.FileHistory;
import cn.gnetop.dcs.dao.schema.GameInfo;
import cn.gnetop.dcs.dao.schema.ServerInfo;
import cn.gnetop.pde.foundation.CommonUtils;
import cn.gnetop.pde.foundation.StringUtils;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/option")
public class OptionController {

	@Autowired
	private OptionService optionService;

	@Autowired
	private LogService logService;

	@Autowired
	private FileAnalyzeService fileAnalyzeService;

	@RequestMapping(value = { "", "/", "/page" })
	public String page(Model model, ServerInfo server) {
		return "option/optionPage";
	}

	@RequestMapping("/fileLoadPage")
	public String fileLoadPage(Model model) {
		List<GameInfo> gameList = logService.getGameList();
		List<ServerInfo> serverList = logService.getServerList();

		model.addAttribute("gameList", gameList);
		model.addAttribute("serverList", serverList);
		return "option/fileLoadPage";
	}

	@RequestMapping("/fileLoadList")
	public String fileLoadList(Model model, FileHistory his) {
		Page<FileHistory> page = optionService.findFileLoadPage(his);
		model.addAttribute("page", page);
		return "option/fileLoadList";
	}

	@RequestMapping("/fileLoadDetail")
	public String fileLoadDetail(Model model) {
		List<File> fileList = Arrays.asList(new File(FileAnalyzeThread.PATH_ROOT).listFiles());
		model.addAttribute("fileList", fileList);
		return "option/fileLoadDetail";
	}

	@ResponseBody
	@RequestMapping("/file")
	public String file(Model model, String path) {
		List<File> fileList = Arrays.asList(new File(CommonUtils.urlDecode(path)).listFiles());
		JSONArray ja = new JSONArray();
		for (File file : fileList) {
			JSONObject jo = new JSONObject();
			jo.put("name", file.getName());
			jo.put("path", file.getPath());
			jo.put("directory", file.isDirectory());
			ja.add(jo);
		}
		return ja.toString();
	}

	@ResponseBody
	@RequestMapping("/fileLoad")
	public String fileLoad(Model model, String account, String dateTime) {
		if (StringUtils.hasNotBlank(account, dateTime)) {
			return fileAnalyzeService.doExecute(account, dateTime);
		}
		return "param error";
	}

	@ResponseBody
	@RequestMapping("/loadTask")
	public String loadTask(Model model, String path) {
		return fileAnalyzeService.doFileExecute(path);
	}

}
