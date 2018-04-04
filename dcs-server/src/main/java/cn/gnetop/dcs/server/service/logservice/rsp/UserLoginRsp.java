package cn.gnetop.dcs.server.service.logservice.rsp;

import cn.gnetop.dcs.system.entity.Response;

public class UserLoginRsp extends Response {

	private static final long serialVersionUID = -5438577033002275517L;

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UserLoginRsp [toString()=");
		builder.append(super.toString());
		builder.append("]");
		return builder.toString();
	}

}
