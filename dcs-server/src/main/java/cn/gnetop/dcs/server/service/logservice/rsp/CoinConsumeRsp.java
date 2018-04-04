package cn.gnetop.dcs.server.service.logservice.rsp;

import cn.gnetop.dcs.system.entity.Response;

public class CoinConsumeRsp extends Response {

	private static final long serialVersionUID = -5507549969371799055L;

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CoinConsumeRsp [toString()=");
		builder.append(super.toString());
		builder.append("]");
		return builder.toString();
	}

}
