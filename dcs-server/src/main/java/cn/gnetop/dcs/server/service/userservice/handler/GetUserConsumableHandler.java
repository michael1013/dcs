package cn.gnetop.dcs.server.service.userservice.handler;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import cn.gnetop.dcs.dao.ApplePayDao;
import cn.gnetop.dcs.dao.schema.ApplePay;
import cn.gnetop.dcs.server.service.userservice.req.GetUserConsumableReq;
import cn.gnetop.dcs.server.service.userservice.rsp.GetUserConsumableRsp;
import cn.gnetop.dcs.system.listener.BeanUtils;

public class GetUserConsumableHandler {
	public GetUserConsumableRsp getUserConsumable(GetUserConsumableReq req) {
		String userid = req.getUserid();
		GetUserConsumableRsp rsp = new GetUserConsumableRsp();
		if (StringUtils.isNotBlank(userid)) {
			ApplePayDao dao = BeanUtils.getBean(ApplePayDao.class);
			ApplePay pay = new ApplePay();
			pay.setUserid(userid);
			pay.setConsumable(new Integer(0));
			List<ApplePay> list = dao.findList(pay);
			List<String> productIds = new ArrayList<>();
			for (ApplePay applePay : list) {
				productIds.add(applePay.getProductid());
			}
			rsp.putData("idList", productIds);
		}
		return rsp;
	}
}
