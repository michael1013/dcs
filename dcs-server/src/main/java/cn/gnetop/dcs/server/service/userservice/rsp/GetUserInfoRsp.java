package cn.gnetop.dcs.server.service.userservice.rsp;

import cn.gnetop.dcs.system.entity.Response;

public class GetUserInfoRsp extends Response {

	private static final long serialVersionUID = -143771581211723954L;

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("GetUserInfoRsp [toString()=");
		builder.append(super.toString());
		builder.append("]");
		return builder.toString();
	}

}
