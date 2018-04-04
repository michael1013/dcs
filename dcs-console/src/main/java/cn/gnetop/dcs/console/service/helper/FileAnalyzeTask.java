package cn.gnetop.dcs.console.service.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.gnetop.dcs.console.service.FileAnalyzeService;
import cn.gnetop.pde.foundation.DateUtils;

@Service
public class FileAnalyzeTask {

	@Autowired
	private FileAnalyzeService fileAnalyzeService;

	public void executeJob() {
		//String dateYestoday = DateUtils.getDateString(DateUtils.getPastDate(1, "day"), "yyyy-MM-dd");
		//fileAnalyzeService.doExecute(dateYestoday);
	}
}
