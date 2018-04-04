package cn.gnetop.dcs.server.service.userservice.handler;

import cn.gnetop.dcs.dao.ApplePayDao;
import cn.gnetop.dcs.dao.schema.ApplePay;
import cn.gnetop.dcs.server.service.userservice.req.LogApplePayReq;
import cn.gnetop.dcs.server.service.userservice.rsp.LogApplePayRsp;
import cn.gnetop.dcs.system.entity.ResultCode;
import cn.gnetop.dcs.system.exception.DcsException;
import cn.gnetop.dcs.system.listener.BeanUtils;
import cn.gnetop.pde.foundation.DateUtils;

public class LogApplePayHandler {

	private ApplePayDao payDao = BeanUtils.getBean(ApplePayDao.class);

	public LogApplePayRsp logApplePay(LogApplePayReq req) throws DcsException {
		ApplePay p = new ApplePay();
		p.setStatus(req.getStatus());
		p.setCreateTime(DateUtils.getDateString());
		int result = payDao.insert(p);
		LogApplePayRsp rsp = new LogApplePayRsp();
		if (1 != result) {
			throw new DcsException(ResultCode.SYS_ERR);
		}
		return rsp;
	}
}
