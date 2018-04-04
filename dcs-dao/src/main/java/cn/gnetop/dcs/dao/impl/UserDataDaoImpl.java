package cn.gnetop.dcs.dao.impl;

import org.springframework.stereotype.Repository;

import cn.gnetop.dcs.dao.UserDataDao;
import cn.gnetop.dcs.dao.base.BaseDaoImpl;
import cn.gnetop.dcs.dao.schema.UserData;

@Repository("userDataDao")
public class UserDataDaoImpl extends BaseDaoImpl<UserData> implements UserDataDao {

}
