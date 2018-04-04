package cn.gnetop.dcs.server.service.logservice.req;

import cn.gnetop.dcs.system.entity.Request;
import cn.gnetop.dcs.system.helper.DcsComment;

@DcsComment(comment = "充值记录")
public class RechargeReq extends Request {

	private static final long serialVersionUID = -6916491697624244395L;

	@DcsComment(comment = "渠道", value = "abc123")
	private String channel;
	@DcsComment(comment = "用户id", value = "abc123")
	private String userid;
	@DcsComment(comment = "用户名", value = "abc123")
	private String username;
	@DcsComment(comment = "订单id", value = "abc123")
	private String orderid;
	@DcsComment(comment = "服务器id", value = "12345678_1")
	private String serverid;
	@DcsComment(comment = "ip", value = "127.127.127.127")
	private String ip;
	@DcsComment(comment = "余额", value = "123456")
	private String amount;
	@DcsComment(comment = "币种", value = "rmb")
	private String currency;
	@DcsComment(comment = "创建时间", value = "2017-01-01 00:00:00")
	private String createTime;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
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

	public String getServerid() {
		return serverid;
	}

	public void setServerid(String serverid) {
		this.serverid = serverid;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RechargeReq [channel=");
		builder.append(channel);
		builder.append(", userid=");
		builder.append(userid);
		builder.append(", orderid=");
		builder.append(orderid);
		builder.append(", serverid=");
		builder.append(serverid);
		builder.append(", ip=");
		builder.append(ip);
		builder.append(", amount=");
		builder.append(amount);
		builder.append(", currency=");
		builder.append(currency);
		builder.append(", createTime=");
		builder.append(createTime);
		builder.append("]");
		return builder.toString();
	}

}
