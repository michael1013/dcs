package cn.gnetop.dcs.server.service.userservice.req;

import cn.gnetop.dcs.system.entity.Request;

public class GetUserConsumableReq extends Request {

	private static final long serialVersionUID = 9010582672554670460L;

	private String userid;

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("GetUserConsumableReq [userid=");
		builder.append(userid);
		builder.append("]");
		return builder.toString();
	}

}
