package cn.gnetop.dcs.server.service.userservice.rsp;

import cn.gnetop.dcs.system.entity.Response;

public class VerifyTokenRsp extends Response {

	private static final long serialVersionUID = 3113136810594803602L;

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("verifyTokenRsp [toString()=");
		builder.append(super.toString());
		builder.append("]");
		return builder.toString();
	}

}
