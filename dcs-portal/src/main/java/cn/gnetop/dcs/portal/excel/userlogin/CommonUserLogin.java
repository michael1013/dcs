package cn.gnetop.dcs.portal.excel.userlogin;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;

public class CommonUserLogin implements Serializable {
	private static final long serialVersionUID = 5908419116136218792L;
	protected String id;
	protected String userid;
	protected String username;
	protected String nickname;
	protected String serverid;
	protected String gameid;
	protected String ip;
	protected String createTime;
	protected String logTime;
	protected String md5;
	protected String file;
	protected boolean isValid = false;

	public boolean isValid() {
		return isValid;
	}

	public String getSql() {
		String sql = "INSERT INTO t_dcs_userlogin_" + this.gameid + "("
		        + (StringUtils.isNoneBlank(userid) ? "userid," : "")
		        + (StringUtils.isNoneBlank(username) ? "username," : "")
		        + (StringUtils.isNoneBlank(username) ? "nickname," : "")
		        + (StringUtils.isNoneBlank(serverid) ? "serverid," : "") + (StringUtils.isNoneBlank(ip) ? "ip," : "")
		        + (StringUtils.isNoneBlank(createTime) ? "create_time," : "")
		        + (StringUtils.isNoneBlank(logTime) ? "log_time," : "") + "md5 ) SELECT "
		        + (StringUtils.isNoneBlank(userid) ? "'" + userid + "'," : "")
		        + (StringUtils.isNoneBlank(username) ? "'" + username + "'," : "")
		        + (StringUtils.isNoneBlank(nickname) ? "'" + nickname + "'," : "")
		        + (StringUtils.isNoneBlank(serverid) ? "'" + serverid + "'," : "")
		        + (StringUtils.isNoneBlank(ip) ? "'" + ip + "'," : "")
		        + (StringUtils.isNoneBlank(createTime) ? "'" + createTime + "'," : "")
		        + (StringUtils.isNoneBlank(logTime) ? "'" + logTime + "'," : "") + "'" + this.md5 + "'"
		        + " FROM DUAL WHERE NOT EXISTS ( SELECT x.id FROM t_dcs_userlogin_" + this.gameid + " x WHERE x.md5 = '"
		        + this.md5 + "' );";
		return sql;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getServerid() {
		return serverid;
	}

	public void setServerid(String serverid) {
		this.serverid = serverid;
	}

	public String getGameid() {
		return gameid;
	}

	public void setGameid(String gameid) {
		this.gameid = gameid;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getLogTime() {
		return logTime;
	}

	public void setLogTime(String logTime) {
		this.logTime = logTime;
	}

	public String getMd5() {
		return md5;
	}

	public void setMd5(String md5) {
		this.md5 = md5;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}

	public String toFileString() {
		StringBuilder builder = new StringBuilder();
		builder.append(StringUtils.isEmpty(userid) ? '-' : userid);
		builder.append('\t');
		builder.append(StringUtils.isEmpty(username) ? '-' : username);
		builder.append('\t');
		builder.append(StringUtils.isEmpty(nickname) ? '-' : nickname);
		builder.append('\t');
		builder.append(StringUtils.isEmpty(ip) ? '-' : ip);
		builder.append('\t');
		builder.append(StringUtils.isEmpty(serverid) ? '-' : serverid);
		builder.append('\t');
		builder.append(StringUtils.isEmpty(logTime) ? '-' : logTime);
		builder.append('\n');
		return builder.toString();
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CommonUserLogin [id=");
		builder.append(id);
		builder.append(", userid=");
		builder.append(userid);
		builder.append(", username=");
		builder.append(username);
		builder.append(", nickname=");
		builder.append(nickname);
		builder.append(", serverid=");
		builder.append(serverid);
		builder.append(", gameid=");
		builder.append(gameid);
		builder.append(", ip=");
		builder.append(ip);
		builder.append(", createTime=");
		builder.append(createTime);
		builder.append(", logTime=");
		builder.append(logTime);
		builder.append(", md5=");
		builder.append(md5);
		builder.append(", file=");
		builder.append(file);
		builder.append(", isValid=");
		builder.append(isValid);
		builder.append("]");
		return builder.toString();
	}

}
