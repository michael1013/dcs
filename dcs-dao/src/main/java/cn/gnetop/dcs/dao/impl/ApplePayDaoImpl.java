package cn.gnetop.dcs.dao.impl;

import org.springframework.stereotype.Repository;

import cn.gnetop.dcs.dao.ApplePayDao;
import cn.gnetop.dcs.dao.base.BaseDaoImpl;
import cn.gnetop.dcs.dao.schema.ApplePay;

@Repository("applePayDao")
public class ApplePayDaoImpl extends BaseDaoImpl<ApplePay> implements ApplePayDao {

}
