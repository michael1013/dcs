package cn.gnetop.dcs.dao;

import cn.gnetop.dcs.dao.base.BaseDao;
import cn.gnetop.dcs.dao.schema.FileHistory;

public interface FileHistoryDao extends BaseDao<FileHistory> {

	FileHistory findByMd5(FileHistory his);
	
}
