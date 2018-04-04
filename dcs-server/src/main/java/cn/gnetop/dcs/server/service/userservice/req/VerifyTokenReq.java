package cn.gnetop.dcs.server.service.userservice.req;

import cn.gnetop.dcs.system.entity.Request;

public class VerifyTokenReq extends Request {

	private static final long serialVersionUID = 7236968446715584207L;

	private String token;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("verifyToken [token=");
		builder.append(token);
		builder.append("]");
		return builder.toString();
	}

}
