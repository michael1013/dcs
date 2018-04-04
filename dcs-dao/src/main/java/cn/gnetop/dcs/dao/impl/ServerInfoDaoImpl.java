package cn.gnetop.dcs.dao.impl;

import org.springframework.stereotype.Repository;

import cn.gnetop.dcs.dao.ServerInfoDao;
import cn.gnetop.dcs.dao.base.BaseDaoImpl;
import cn.gnetop.dcs.dao.schema.ServerInfo;

@Repository("serverInfoDao")
public class ServerInfoDaoImpl extends BaseDaoImpl<ServerInfo> implements ServerInfoDao {

}
