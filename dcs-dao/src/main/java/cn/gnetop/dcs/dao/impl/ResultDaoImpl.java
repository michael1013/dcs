package cn.gnetop.dcs.dao.impl;

import org.springframework.stereotype.Repository;

import cn.gnetop.dcs.dao.ResultDao;
import cn.gnetop.dcs.dao.base.BaseDaoImpl;
import cn.gnetop.dcs.dao.schema.Result;

@Repository("resultDao")
public class ResultDaoImpl extends BaseDaoImpl<Result> implements ResultDao {

}
