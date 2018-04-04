package cn.gnetop.dcs.dao.schema;

import cn.gnetop.dcs.dao.base.DataEntity;

public class ServerInfo extends DataEntity {

	private static final long serialVersionUID = -3160856619236581897L;

	private String gameid;
	private String gameName;
	private String serverid;
	private String serverName;
	private String openTime;
	private String closeTime;
	private String integrateTime;
	private String createTime;

	public String getGameName() {
		return gameName;
	}

	public void setGameName(String gameName) {
		this.gameName = gameName;
	}

	public String getGameid() {
		return gameid;
	}

	public void setGameid(String gameid) {
		this.gameid = gameid;
	}

	public String getServerName() {
		return serverName;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	public String getServerid() {
		return serverid;
	}

	public void setServerid(String serverid) {
		this.serverid = serverid;
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

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ServerInfo [gameid=");
		builder.append(gameid);
		builder.append(", gameName=");
		builder.append(gameName);
		builder.append(", serverid=");
		builder.append(serverid);
		builder.append(", serverName=");
		builder.append(serverName);
		builder.append(", openTime=");
		builder.append(openTime);
		builder.append(", closeTime=");
		builder.append(closeTime);
		builder.append(", integrateTime=");
		builder.append(integrateTime);
		builder.append(", createTime=");
		builder.append(createTime);
		builder.append("]");
		return builder.toString();
	}

}
