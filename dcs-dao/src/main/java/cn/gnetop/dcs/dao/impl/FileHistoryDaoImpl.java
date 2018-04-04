package cn.gnetop.dcs.dao.impl;

import org.springframework.stereotype.Repository;

import cn.gnetop.dcs.dao.FileHistoryDao;
import cn.gnetop.dcs.dao.base.BaseDaoImpl;
import cn.gnetop.dcs.dao.schema.FileHistory;

@Repository("fileHistoryDao")
public class FileHistoryDaoImpl extends BaseDaoImpl<FileHistory> implements FileHistoryDao {

	@Override
	public FileHistory findByMd5(FileHistory his) {
		return sqlSessionTemplate.selectOne(sqlNamespace + ".findByMd5", his);
	}

}
