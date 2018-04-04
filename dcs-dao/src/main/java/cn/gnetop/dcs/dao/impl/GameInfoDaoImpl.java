package cn.gnetop.dcs.dao.impl;

import org.springframework.stereotype.Repository;

import cn.gnetop.dcs.dao.GameInfoDao;
import cn.gnetop.dcs.dao.base.BaseDaoImpl;
import cn.gnetop.dcs.dao.schema.GameInfo;

@Repository("gameInfoDao")
public class GameInfoDaoImpl extends BaseDaoImpl<GameInfo> implements GameInfoDao {

}
