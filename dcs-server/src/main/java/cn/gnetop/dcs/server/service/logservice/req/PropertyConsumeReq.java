package cn.gnetop.dcs.server.service.logservice.req;

import cn.gnetop.dcs.system.entity.Request;
import cn.gnetop.dcs.system.helper.DcsComment;

@DcsComment(comment = "游戏道具消耗")
public class PropertyConsumeReq extends Request {

	private static final long serialVersionUID = 1725044063242322375L;
	@DcsComment(comment = "用户id", value = "123456")
	private String userid;
	@DcsComment(comment = "服务器id", value = "123456")
	private String serverid;
	@DcsComment(comment = "类型", value = "abc123")
	private String type;
	@DcsComment(comment = "道具名称", value = "abc123")
	private String name;
	@DcsComment(comment = "价格", value = "abc123")
	private String price;
	@DcsComment(comment = "消耗量", value = "123456")
	private String consume;
	@DcsComment(comment = "余额", value = "123456")
	private String amount;
	@DcsComment(comment = "创建时间", value = "2-17-01-01 00:00:00")
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

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getConsume() {
		return consume;
	}

	public void setConsume(String consume) {
		this.consume = consume;
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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PropertyConsumeReq [userid=");
		builder.append(userid);
		builder.append(", serverid=");
		builder.append(serverid);
		builder.append(", type=");
		builder.append(type);
		builder.append(", name=");
		builder.append(name);
		builder.append(", price=");
		builder.append(price);
		builder.append(", consume=");
		builder.append(consume);
		builder.append(", amount=");
		builder.append(amount);
		builder.append(", createTime=");
		builder.append(createTime);
		builder.append("]");
		return builder.toString();
	}

}
