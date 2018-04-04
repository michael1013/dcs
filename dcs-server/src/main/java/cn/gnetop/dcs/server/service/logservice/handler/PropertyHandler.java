package cn.gnetop.dcs.server.service.logservice.handler;

import cn.gnetop.dcs.dao.PropertyDao;
import cn.gnetop.dcs.dao.schema.Property;
import cn.gnetop.dcs.server.service.logservice.req.PropertyReq;
import cn.gnetop.dcs.server.service.logservice.rsp.PropertyRsp;
import cn.gnetop.dcs.system.listener.BeanUtils;
import cn.gnetop.pde.foundation.DateUtils;

public class PropertyHandler {
	public PropertyRsp property(PropertyReq req) {
		Property p = new Property();
		
		p.setPropertyid(req.getPropertyid());
		p.setType(req.getType());
		p.setPrice(req.getPrice());
		p.setGameid(req.getGameid());
		p.setCreateTime(DateUtils.getDateString());
		
		PropertyDao dao = BeanUtils.getBean(PropertyDao.class);
		dao.insert(p);

		PropertyRsp rsp = new PropertyRsp();
		return rsp;
	}
}
