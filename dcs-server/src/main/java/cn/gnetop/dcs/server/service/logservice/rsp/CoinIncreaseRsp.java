package cn.gnetop.dcs.server.service.logservice.rsp;

import cn.gnetop.dcs.system.entity.Response;

public class CoinIncreaseRsp extends Response {

	private static final long serialVersionUID = -8771817857316712511L;

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CoinIncreaseRsp [toString()=");
		builder.append(super.toString());
		builder.append("]");
		return builder.toString();
	}

}
