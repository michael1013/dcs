package cn.gnetop.dcs.server.service.logservice.handler;

import org.apache.commons.lang.StringUtils;

import cn.gnetop.dcs.dao.PropertyConsumeDao;
import cn.gnetop.dcs.dao.schema.PropertyConsume;
import cn.gnetop.dcs.server.service.logservice.req.PropertyConsumeReq;
import cn.gnetop.dcs.server.service.logservice.rsp.PropertyConsumeRsp;
import cn.gnetop.dcs.system.listener.BeanUtils;
import cn.gnetop.pde.foundation.DateUtils;
import cn.gnetop.pde.foundation.encryte.MD5Utils;

public class PropertyConsumeHandler {
	public PropertyConsumeRsp propertyConsume(PropertyConsumeReq req) {
		PropertyConsume consume = new PropertyConsume();
		consume.setUserid(req.getUserid());
		consume.setServerid(req.getServerid());
		consume.setType(req.getType());
		consume.setName(req.getName());
		consume.setPrice(req.getPrice());
		consume.setConsume(req.getConsume());
		consume.setAmount(req.getAmount());
		consume.setCreateTime(req.getCreateTime());
		consume.setLogTime(DateUtils.getDateString());
		consume.setMd5(MD5Utils.encrypt(req.toString()));

		if (StringUtils.isBlank(consume.getCreateTime())) {
			consume.setCreateTime(DateUtils.getDateString());
		}

		PropertyConsumeDao dao = BeanUtils.getBean(PropertyConsumeDao.class);
		dao.insert(consume);

		PropertyConsumeRsp rsp = new PropertyConsumeRsp();
		return rsp;
	}
}
