package cn.gnetop.dcs.dao.impl;

import org.springframework.stereotype.Repository;

import cn.gnetop.dcs.dao.AdminPasswordDao;
import cn.gnetop.dcs.dao.base.BaseDaoImpl;
import cn.gnetop.dcs.dao.schema.AdminPassword;

@Repository("adminPasswordDao")
public class AdminPasswordDaoImpl extends BaseDaoImpl<AdminPassword> implements AdminPasswordDao {

}
