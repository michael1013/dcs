package cn.gnetop.dcs.server.service.logservice.handler;

import cn.gnetop.dcs.dao.PropertyIncreaseDao;
import cn.gnetop.dcs.dao.schema.PropertyIncrease;
import cn.gnetop.dcs.server.service.logservice.req.PropertyIncreaseReq;
import cn.gnetop.dcs.server.service.logservice.rsp.PropertyIncreaseRsp;
import cn.gnetop.dcs.system.listener.BeanUtils;
import cn.gnetop.pde.foundation.DateUtils;
import cn.gnetop.pde.foundation.encryte.MD5Utils;

public class PropertyIncreaseHandler {
	public PropertyIncreaseRsp propertyIncrease(PropertyIncreaseReq req) {
		PropertyIncrease increase = new PropertyIncrease();
		increase.setUserid(req.getUserid());
		increase.setServerid(req.getServerid());
		increase.setType(req.getType());
		increase.setName(req.getName());
		increase.setPrice(req.getPrice());
		increase.setIncrease(req.getIncrease());
		increase.setAmount(req.getAmount());
		increase.setCreateTime(req.getCreateTime());
		increase.setLogTime(DateUtils.getDateString());
		increase.setMd5(MD5Utils.encrypt(req.toString()));

		PropertyIncreaseDao dao = BeanUtils.getBean(PropertyIncreaseDao.class);
		dao.insert(increase);

		PropertyIncreaseRsp rsp = new PropertyIncreaseRsp();
		return rsp;
	}
}
