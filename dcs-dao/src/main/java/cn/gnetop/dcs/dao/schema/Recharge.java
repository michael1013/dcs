package cn.gnetop.dcs.dao.schema;

import cn.gnetop.dcs.dao.base.DataEntity;

public class Recharge extends DataEntity {
	private static final long serialVersionUID = 5908419116136218792L;
	private String id;
	private String channel;
	private String userid;
	private String username;
	private String orderid;
	private String serverid;
	private String gameid;
	private String ip;
	private String amount;
	private String currency;
	private String createTime;
	private String logTime;
	private String md5;
	private String file;

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

}
