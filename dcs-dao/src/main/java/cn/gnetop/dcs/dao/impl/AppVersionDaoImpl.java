package cn.gnetop.dcs.dao.impl;

import org.springframework.stereotype.Repository;

import cn.gnetop.dcs.dao.AppVersionDao;
import cn.gnetop.dcs.dao.base.BaseDaoImpl;
import cn.gnetop.dcs.dao.schema.AppVersion;

@Repository("appVersionDao")
public class AppVersionDaoImpl extends BaseDaoImpl<AppVersion> implements AppVersionDao {

}
