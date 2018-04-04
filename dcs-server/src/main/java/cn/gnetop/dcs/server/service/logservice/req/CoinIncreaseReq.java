package cn.gnetop.dcs.server.service.logservice.req;

import cn.gnetop.dcs.system.entity.Request;
import cn.gnetop.dcs.system.helper.DcsComment;

@DcsComment(comment = "游戏金币增加")
public class CoinIncreaseReq extends Request {

	private static final long serialVersionUID = -5197624830097156260L;
	@DcsComment(comment = "用户id", value = "123456")
	private String userid;
	@DcsComment(comment = "服务器id", value = "123456")
	private String serverid;
	@DcsComment(comment = "增加类型", value = "abc123")
	private String type;
	@DcsComment(comment = "货币名称", value = "abc123")
	private String name;
	@DcsComment(comment = "余额", value = "123456")
	private String amount;
	@DcsComment(comment = "增加量", value = "123456")
	private String increase;
	@DcsComment(comment = "创建时间", value = "2016-01-01 00:00:00")
	private String createTime;

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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CoinIncreaseReq [userid=");
		builder.append(userid);
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
		builder.append("]");
		return builder.toString();
	}

}
