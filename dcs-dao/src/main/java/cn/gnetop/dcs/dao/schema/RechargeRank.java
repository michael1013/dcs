package cn.gnetop.dcs.dao.schema;

import cn.gnetop.dcs.dao.base.DataEntity;

public class RechargeRank extends DataEntity {

	private static final long serialVersionUID = -8758869054332089286L;

	private String gameid;
	private String serverid;
	private String userid;
	private Integer rank;
	private String amount;
	private String firstLoginTime;
	private String lastLoginTime;

	public String getGameid() {
		return gameid;
	}

	public void setGameid(String gameid) {
		this.gameid = gameid;
	}

	public String getServerid() {
		return serverid;
	}

	public void setServerid(String serverid) {
		this.serverid = serverid;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public Integer getRank() {
		return rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getFirstLoginTime() {
		return firstLoginTime;
	}

	public void setFirstLoginTime(String firstLoginTime) {
		this.firstLoginTime = firstLoginTime;
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
		builder.append("RechargeRank [gameid=");
		builder.append(gameid);
		builder.append(", serverid=");
		builder.append(serverid);
		builder.append(", userid=");
		builder.append(userid);
		builder.append(", rank=");
		builder.append(rank);
		builder.append(", amount=");
		builder.append(amount);
		builder.append(", firstLoginTime=");
		builder.append(firstLoginTime);
		builder.append(", lastLoginTime=");
		builder.append(lastLoginTime);
		builder.append("]");
		return builder.toString();
	}

}
