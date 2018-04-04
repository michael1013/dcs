package cn.gnetop.dcs.portal.excel.entity;

import org.apache.commons.lang3.StringUtils;

public class CommonRecharge extends DataEntity {
	private static final long serialVersionUID = 5908419116136218792L;
	protected String id;
	protected String channel;
	protected String userid;
	protected String username;
	protected String orderid;
	protected String serverid;
	protected String gameid;
	protected String ip;
	protected String amount;
	protected String currency;
	protected String createTime;
	protected String logTime;
	protected String md5;
	protected String file;
	protected boolean isValid = false;

	public boolean isValid() {
		return isValid;
	}

	public String getSql() {
		String sql = "INSERT INTO t_dcs_recharge_" + this.gameid + "("
		        + (StringUtils.isNoneBlank(userid) ? "userid," : "")
		        + (StringUtils.isNoneBlank(username) ? "username," : "")
		        + (StringUtils.isNoneBlank(serverid) ? "serverid," : "")
		        + (StringUtils.isNoneBlank(channel) ? "channel," : "")
		        + (StringUtils.isNoneBlank(orderid) ? "orderid," : "") + (StringUtils.isNoneBlank(ip) ? "ip," : "")
		        + (StringUtils.isNoneBlank(amount) ? "amount," : "")
		        + (StringUtils.isNoneBlank(currency) ? "currency," : "")
		        + (StringUtils.isNoneBlank(createTime) ? "create_time," : "")
		        + (StringUtils.isNoneBlank(logTime) ? "log_time," : "") + "md5 ) SELECT "
		        + (StringUtils.isNoneBlank(userid) ? "'" + userid + "'," : "")
		        + (StringUtils.isNoneBlank(username) ? "'" + username + "'," : "")
		        + (StringUtils.isNoneBlank(serverid) ? "'" + serverid + "'," : "")
		        + (StringUtils.isNoneBlank(channel) ? "'" + channel + "'," : "")
		        + (StringUtils.isNoneBlank(orderid) ? "'" + orderid + "'," : "")
		        + (StringUtils.isNoneBlank(ip) ? "'" + ip + "'," : "")
		        + (StringUtils.isNoneBlank(amount) ? "'" + amount + "'," : "")
		        + (StringUtils.isNoneBlank(currency) ? "'" + currency + "'," : "")
		        + (StringUtils.isNoneBlank(createTime) ? "'" + createTime + "'," : "")
		        + (StringUtils.isNoneBlank(logTime) ? "'" + logTime + "'," : "") + "'" + this.md5 + "'"
		        + " FROM DUAL WHERE NOT EXISTS ( SELECT x.id FROM t_dcs_recharge_" + this.gameid + " x WHERE x.md5 = '"
		        + this.md5 + "' );";
		return sql;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getGameid() {
		return gameid;
	}

	public void setGameid(String gameid) {
		this.gameid = gameid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getMd5() {
		return md5;
	}

	public void setMd5(String md5) {
		this.md5 = md5;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	public String getServerid() {
		return serverid;
	}

	public void setServerid(String serverid) {
		this.serverid = serverid;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Recharge [id=");
		builder.append(id);
		builder.append(", channel=");
		builder.append(channel);
		builder.append(", userid=");
		builder.append(userid);
		builder.append(", username=");
		builder.append(username);
		builder.append(", orderid=");
		builder.append(orderid);
		builder.append(", serverid=");
		builder.append(serverid);
		builder.append(", gameid=");
		builder.append(gameid);
		builder.append(", ip=");
		builder.append(ip);
		builder.append(", amount=");
		builder.append(amount);
		builder.append(", currency=");
		builder.append(currency);
		builder.append(", createTime=");
		builder.append(createTime);
		builder.append(", logTime=");
		builder.append(logTime);
		builder.append(", md5=");
		builder.append(md5);
		builder.append("]");
		return builder.toString();
	}

	public String toFileString() {
		StringBuilder builder = new StringBuilder();
		builder.append(StringUtils.isEmpty(channel) ? '-' : channel);
		builder.append('\t');
		builder.append(StringUtils.isEmpty(userid) ? '-' : userid);
		builder.append('\t');
		builder.append(StringUtils.isEmpty(username) ? '-' : username);
		builder.append('\t');
		builder.append(StringUtils.isEmpty(orderid) ? '-' : orderid);
		builder.append('\t');
		builder.append(StringUtils.isEmpty(ip) ? '-' : ip);
		builder.append('\t');
		builder.append(StringUtils.isEmpty(amount) ? '-' : amount);
		builder.append('\t');
		builder.append(StringUtils.isEmpty(currency) ? "CNY" : currency);
		builder.append('\t');
		builder.append(StringUtils.isEmpty(logTime) ? '-' : logTime);
		builder.append('\n');
		return builder.toString();
	}

}
