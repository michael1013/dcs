package cn.gnetop.dcs.console.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.gnetop.dcs.console.service.helper.FileAnalyzeThread;
import cn.gnetop.dcs.dao.FileHistoryDao;
import cn.gnetop.dcs.dao.schema.FileHistory;
import cn.gnetop.pde.foundation.DateUtils;
import cn.gnetop.pde.foundation.config.GlobalConfig;
import cn.gnetop.pde.foundation.encryte.MD5Utils;

@Service
public class FileAnalyzeService {

	private static final String[] PATH_ACCOUNTS = GlobalConfig.getConfig("log.file.account", "").split("\\|");
	
	@Autowired
	private FileHistoryDao fileHistoryDao;

	public String doExecute(String dateTime) {
		try {
			Date date = DateUtils.parseDate(dateTime, "yyyy-MM-dd");
			for (String account : PATH_ACCOUNTS) {
				new Thread(new FileAnalyzeThread(date, account)).start();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
		return "success";
	}

	public String doExecute(String account, String dateTime) {
		try {
			Date date = DateUtils.parseDate(dateTime, "yyyy-MM-dd");
			new Thread(new FileAnalyzeThread(date, account)).start();
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
		return "success";
	}

	public String doFileExecute(String path) {
		File file = new File(path);
		if (file.exists()) {
			try {
				String md5 = MD5Utils.encrypt(file);
				FileHistory his = new FileHistory();
				his.setMd5(md5);
				his = fileHistoryDao.findByMd5(his);
				if (null != his) {
					return "file already read";
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				return "file not exists";
			}
		}else{
			return "file not exists";
		}
		
		try {
			new Thread(new FileAnalyzeThread(path)).start();
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
		return "success";
	}
}
