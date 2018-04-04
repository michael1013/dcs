package cn.gnetop.dcs.server.service.logservice.rsp;

import cn.gnetop.dcs.system.entity.Response;

public class RechargeRsp extends Response {

	private static final long serialVersionUID = 2378124446530648980L;

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RechargeRsp [toString()=");
		builder.append(super.toString());
		builder.append("]");
		return builder.toString();
	}

}
