package cn.gnetop.dcs.server.service.userservice.rsp;

import cn.gnetop.dcs.system.entity.Response;

public class UpdatePasswordRsp extends Response {

	private static final long serialVersionUID = -5337110787257657820L;

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UpdatePasswordRsp [toString()=");
		builder.append(super.toString());
		builder.append("]");
		return builder.toString();
	}

}
