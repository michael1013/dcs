package cn.gnetop.dcs.server.service.logservice.rsp;

import cn.gnetop.dcs.system.entity.Response;

public class ServerInfoRsp extends Response {

	private static final long serialVersionUID = -4283075857024655672L;

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ServerInfoRsp [toString()=");
		builder.append(super.toString());
		builder.append("]");
		return builder.toString();
	}

}
