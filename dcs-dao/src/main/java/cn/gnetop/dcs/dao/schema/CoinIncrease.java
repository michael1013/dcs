package cn.gnetop.dcs.dao.schema;

import cn.gnetop.dcs.dao.base.DataEntity;

public class CoinIncrease extends DataEntity {

	private static final long serialVersionUID = -6593727553937310962L;

	private String id;
	private String userid;
	private String username;
	private String gameid;
	private String serverid;
	private String type;
	private String name;
	private String amount;
	private String increase;
	private String createTime;
	private String logTime;
	private String md5;

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

	public String getServerid() {
		return serverid;
	}

	public void setServerid(String serverid) {
		this.serverid = serverid;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getIncrease() {
		return increase;
	}

	public void setIncrease(String increase) {
		this.increase = increase;
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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CoinIncrease [id=");
		builder.append(id);
		builder.append(", userid=");
		builder.append(userid);
		builder.append(", username=");
		builder.append(username);
		builder.append(", gameid=");
		builder.append(gameid);
		builder.append(", serverid=");
		builder.append(serverid);
		builder.append(", type=");
		builder.append(type);
		builder.append(", name=");
		builder.append(name);
		builder.append(", amount=");
		builder.append(amount);
		builder.append(", increase=");
		builder.append(increase);
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
