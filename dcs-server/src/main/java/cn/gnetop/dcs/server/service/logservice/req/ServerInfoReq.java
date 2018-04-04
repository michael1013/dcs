package cn.gnetop.dcs.server.service.logservice.req;

import cn.gnetop.dcs.system.entity.Request;
import cn.gnetop.dcs.system.helper.DcsComment;

@DcsComment(comment = "服务器信息")
public class ServerInfoReq extends Request {
	private static final long serialVersionUID = -1310757124130064570L;
	@DcsComment(comment = "服务器id", value = "123456")
	private String newserverid;
	@DcsComment(comment = "服务器名称", value = "abc123")
	private String serverName;
	@DcsComment(comment = "开放时间", value = "2017-01-01 00:00:00")
	private String openTime;
	@DcsComment(comment = "关闭时间", value = "2017-01-01 00:00:00")
	private String closeTime;
	@DcsComment(comment = "合并时间", value = "2017-01-01 00:00:00")
	private String integrateTime;

	public String getNewserverid() {
		return newserverid;
	}

	public void setNewserverid(String newserverid) {
		this.newserverid = newserverid;
	}

	public String getServerName() {
		return serverName;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	public String getOpenTime() {
		return openTime;
	}

	public void setOpenTime(String openTime) {
		this.openTime = openTime;
	}

	public String getCloseTime() {
		return closeTime;
	}

	public void setCloseTime(String closeTime) {
		this.closeTime = closeTime;
	}

	public String getIntegrateTime() {
		return integrateTime;
	}

	public void setIntegrateTime(String integrateTime) {
		this.integrateTime = integrateTime;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ServerInfoReq [newserverid=");
		builder.append(newserverid);
		builder.append(", openTime=");
		builder.append(openTime);
		builder.append(", closeTime=");
		builder.append(closeTime);
		builder.append(", integrateTime=");
		builder.append(integrateTime);
		builder.append("]");
		return builder.toString();
	}

}
