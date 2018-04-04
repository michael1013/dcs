package cn.gnetop.dcs.console.service.impl;

import org.springframework.stereotype.Service;

import cn.gnetop.dcs.console.service.ApplePayService;
import cn.gnetop.dcs.console.service.base.BaseServiceImpl;
import cn.gnetop.dcs.dao.mapper.ApplePayMapperDaoImpl;
import cn.gnetop.dcs.dao.schema.ApplePay;

@Service
public class ApplePayServiceImpl extends BaseServiceImpl<ApplePay, ApplePayMapperDaoImpl> implements ApplePayService {

}
