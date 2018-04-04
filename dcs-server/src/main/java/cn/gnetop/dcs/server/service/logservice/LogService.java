package cn.gnetop.dcs.server.service.logservice;

import cn.gnetop.dcs.server.service.logservice.req.CoinConsumeReq;
import cn.gnetop.dcs.server.service.logservice.req.CoinIncreaseReq;
import cn.gnetop.dcs.server.service.logservice.req.FlushConfigReq;
import cn.gnetop.dcs.server.service.logservice.req.PropertyConsumeReq;
import cn.gnetop.dcs.server.service.logservice.req.PropertyIncreaseReq;
import cn.gnetop.dcs.server.service.logservice.req.RechargeReq;
import cn.gnetop.dcs.server.service.logservice.req.ServerInfoReq;
import cn.gnetop.dcs.server.service.logservice.req.UserLoginReq;
import cn.gnetop.dcs.server.service.logservice.rsp.CoinConsumeRsp;
import cn.gnetop.dcs.server.service.logservice.rsp.CoinIncreaseRsp;
import cn.gnetop.dcs.server.service.logservice.rsp.FlushConfigRsp;
import cn.gnetop.dcs.server.service.logservice.rsp.PropertyConsumeRsp;
import cn.gnetop.dcs.server.service.logservice.rsp.PropertyIncreaseRsp;
import cn.gnetop.dcs.server.service.logservice.rsp.RechargeRsp;
import cn.gnetop.dcs.server.service.logservice.rsp.ServerInfoRsp;
import cn.gnetop.dcs.server.service.logservice.rsp.UserLoginRsp;
import cn.gnetop.dcs.system.engine.IService;
import cn.gnetop.dcs.system.exception.DcsException;

public interface LogService extends IService {

	CoinConsumeRsp coinConsume(CoinConsumeReq req);

	CoinIncreaseRsp coinIncrease(CoinIncreaseReq req);

	PropertyConsumeRsp propertyConsume(PropertyConsumeReq req);

	PropertyIncreaseRsp propertyIncrease(PropertyIncreaseReq req);

	RechargeRsp recharge(RechargeReq req);

	ServerInfoRsp serverInfo(ServerInfoReq req);

	UserLoginRsp userLogin(UserLoginReq req);
	
	FlushConfigRsp flushConfig(FlushConfigReq req) throws DcsException;
}
