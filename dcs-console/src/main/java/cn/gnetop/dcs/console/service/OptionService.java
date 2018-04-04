package cn.gnetop.dcs.console.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.gnetop.dcs.dao.FileHistoryDao;
import cn.gnetop.dcs.dao.base.Page;
import cn.gnetop.dcs.dao.schema.FileHistory;

@Service
public class OptionService {

	@Autowired
	private FileHistoryDao fileHistoryDao;

	public Page<FileHistory> findFileLoadPage(FileHistory his) {
		Integer total = fileHistoryDao.countFindList(his);
		Integer pageNo = his.getPageNo();
		Integer pageSize = his.getPageSize();
		Integer start = (pageNo - 1) * pageSize;
		his.setStart(start);
		Page<FileHistory> page = new Page<>();
		List<FileHistory> list = fileHistoryDao.findList(his);
		page.setList(list);
		page.setTotal(total);
		page.setStart(start);
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		return page;
	}

}
