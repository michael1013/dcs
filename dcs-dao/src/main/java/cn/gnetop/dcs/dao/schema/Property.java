package cn.gnetop.dcs.dao.schema;

import cn.gnetop.dcs.dao.base.DataEntity;

public class Property extends DataEntity {

	private static final long serialVersionUID = 4357128181470432870L;

	private String propertyid;
	private String propertyName;
	private String gameid;
	private String serverid;
	private String type;
	private String price;
	private String createTime;

	public String getGameid() {
		return gameid;
	}

	public void setGameid(String gameid) {
		this.gameid = gameid;
	}

	public String getPropertyName() {
		return propertyName;
	}

	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

	public String getServerid() {
		return serverid;
	}

	public void setServerid(String serverid) {
		this.serverid = serverid;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getPropertyid() {
		return propertyid;
	}

	public void setPropertyid(String propertyid) {
		this.propertyid = propertyid;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Property [propertyid=");
		builder.append(propertyid);
		builder.append(", propertyName=");
		builder.append(propertyName);
		builder.append(", gameid=");
		builder.append(gameid);
		builder.append(", serverid=");
		builder.append(serverid);
		builder.append(", type=");
		builder.append(type);
		builder.append(", price=");
		builder.append(price);
		builder.append(", createTime=");
		builder.append(createTime);
		builder.append("]");
		return builder.toString();
	}

}
