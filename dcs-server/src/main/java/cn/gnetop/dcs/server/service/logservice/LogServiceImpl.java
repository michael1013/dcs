package cn.gnetop.dcs.server.service.logservice;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.gnetop.dcs.server.service.logservice.handler.CoinConsumeHandler;
import cn.gnetop.dcs.server.service.logservice.handler.CoinIncreaseHandler;
import cn.gnetop.dcs.server.service.logservice.handler.FlushConfigHandler;
import cn.gnetop.dcs.server.service.logservice.handler.PropertyConsumeHandler;
import cn.gnetop.dcs.server.service.logservice.handler.PropertyIncreaseHandler;
import cn.gnetop.dcs.server.service.logservice.handler.RechargeHandler;
import cn.gnetop.dcs.server.service.logservice.handler.ServerInfoHandler;
import cn.gnetop.dcs.server.service.logservice.handler.UserLoginHandler;
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
import cn.gnetop.dcs.system.exception.DcsException;

@Transactional
@Service("logService")
public class LogServiceImpl implements LogService {

	@Override
	public UserLoginRsp userLogin(UserLoginReq req) {
		return new UserLoginHandler().userLogin(req);
	}

	@Override
	public CoinConsumeRsp coinConsume(CoinConsumeReq req) {
		return new CoinConsumeHandler().coinConsume(req);
	}

	@Override
	public CoinIncreaseRsp coinIncrease(CoinIncreaseReq req) {
		return new CoinIncreaseHandler().coinIncrease(req);
	}

	@Override
	public PropertyConsumeRsp propertyConsume(PropertyConsumeReq req) {
		return new PropertyConsumeHandler().propertyConsume(req);
	}

	@Override
	public PropertyIncreaseRsp propertyIncrease(PropertyIncreaseReq req) {
		return new PropertyIncreaseHandler().propertyIncrease(req);
	}

	@Override
	public RechargeRsp recharge(RechargeReq req) {
		return new RechargeHandler().recharge(req);
	}

	@Override
	public ServerInfoRsp serverInfo(ServerInfoReq req) {
		return new ServerInfoHandler().serverInfo(req);
	}

	@Override
	public FlushConfigRsp flushConfig(FlushConfigReq req) throws DcsException {
		return new FlushConfigHandler().flushConfig(req);
	}

}
