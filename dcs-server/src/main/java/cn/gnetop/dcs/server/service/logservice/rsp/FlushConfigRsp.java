package cn.gnetop.dcs.server.service.logservice.rsp;

import cn.gnetop.dcs.system.entity.Response;

public class FlushConfigRsp extends Response {
	
	private static final long serialVersionUID = -27755037109263396L;

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FlushConfigRsp [toString()=");
		builder.append(super.toString());
		builder.append("]");
		return builder.toString();
	}

}
