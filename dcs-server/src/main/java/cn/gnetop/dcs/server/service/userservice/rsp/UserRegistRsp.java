package cn.gnetop.dcs.server.service.userservice.rsp;

import cn.gnetop.dcs.system.entity.Response;

public class UserRegistRsp extends Response {

	private static final long serialVersionUID = 8964487242264204463L;

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UserRegistRsp [toString()=");
		builder.append(super.toString());
		builder.append("]");
		return builder.toString();
	}

}
