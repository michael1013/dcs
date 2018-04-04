package cn.gnetop.dcs.server.service.userservice.rsp;

import cn.gnetop.dcs.system.entity.Response;

public class ResetPasswordRsp extends Response {

	private static final long serialVersionUID = -4356563757136523395L;

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ResetPasswordRsp [toString()=");
		builder.append(super.toString());
		builder.append("]");
		return builder.toString();
	}

}
