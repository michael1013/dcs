package cn.gnetop.dcs.dao.schema;

import cn.gnetop.dcs.dao.base.DataEntity;

/**
 * 数据sdk对接的游戏用户对象
 * 
 * @author luke
 *
 */
public class UserInfo extends DataEntity {

	private static final long serialVersionUID = 8419793826790818874L;

	private String userid;
	private String username;
	private String serverid;
	private String nickname;
	private String createTime;
	private String updateTime;
	private String lastLoginTime;
	private String logTime;

	public String getLogTime() {
		return logTime;
	}

	public void setLogTime(String logTime) {
		this.logTime = logTime;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getServerid() {
		return serverid;
	}

	public void setServerid(String serverid) {
		this.serverid = serverid;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(String lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UserInfo [userid=");
		builder.append(userid);
		builder.append(", username=");
		builder.append(username);
		builder.append(", serverid=");
		builder.append(serverid);
		builder.append(", nickname=");
		builder.append(nickname);
		builder.append(", createTime=");
		builder.append(createTime);
		builder.append(", updateTime=");
		builder.append(updateTime);
		builder.append(", lastLoginTime=");
		builder.append(lastLoginTime);
		builder.append(", logTime=");
		builder.append(logTime);
		builder.append("]");
		return builder.toString();
	}

}
