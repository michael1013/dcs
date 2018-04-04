package cn.gnetop.dcs.dao.impl;

import org.springframework.stereotype.Repository;

import cn.gnetop.dcs.dao.UserPasswordDao;
import cn.gnetop.dcs.dao.base.BaseDaoImpl;
import cn.gnetop.dcs.dao.schema.UserPassword;

@Repository("userPasswordDao")
public class UserPasswordDaoImpl extends BaseDaoImpl<UserPassword> implements UserPasswordDao {

}
