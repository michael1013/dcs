package cn.gnetop.dcs.dao.impl;

import org.springframework.stereotype.Repository;

import cn.gnetop.dcs.dao.PasswordDao;
import cn.gnetop.dcs.dao.base.BaseDaoImpl;
import cn.gnetop.dcs.dao.schema.Password;

@Repository("passwordDao")
public class PasswordDaoImpl extends BaseDaoImpl<Password> implements PasswordDao {

}
