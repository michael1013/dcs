package cn.gnetop.dcs.server.service.userservice.handler;

import cn.gnetop.dcs.dao.ApplePayDao;
import cn.gnetop.dcs.dao.schema.ApplePay;
import cn.gnetop.dcs.server.service.userservice.req.GetApplePayReq;
import cn.gnetop.dcs.server.service.userservice.rsp.GetApplePayRsp;
import cn.gnetop.dcs.system.listener.BeanUtils;

public class GetApplePayHandler {

	private ApplePayDao payDao = BeanUtils.getBean(ApplePayDao.class);

	public GetApplePayRsp getApplePay(GetApplePayReq req) {
		ApplePay p = new ApplePay();
		// p.setStatus(req.getStatus());
		// List<ApplePay> list = payDao.findList(p);
		GetApplePayRsp rsp = new GetApplePayRsp();
		// rsp.putData("applePayList", ApplePayConvertor.convertor(list));
		return rsp;
	}
}
