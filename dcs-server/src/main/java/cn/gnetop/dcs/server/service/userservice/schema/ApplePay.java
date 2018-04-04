package cn.gnetop.dcs.server.service.userservice.schema;

import java.io.Serializable;

public class ApplePay implements Serializable {

	private static final long serialVersionUID = -2985966719060685327L;

	private String id;
	private String orderid;
	private String transid;
	private String channel;
	private String appid;
	private String ordername;
	private Double price;
	private String userid;
	private String username;
	private String attach;
	private String status;
	private Integer sandbox;
	private String createat;
	private String startat;
	private String payat;
	private String sign;
	private String createTime;
	private String updateTime;
	private Integer delFlag;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	public String getTransid() {
		return transid;
	}

	public void setTransid(String transid) {
		this.transid = transid;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getOrdername() {
		return ordername;
	}

	public void setOrdername(String ordername) {
		this.ordername = ordername;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
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

	public String getAttach() {
		return attach;
	}

	public void setAttach(String attach) {
		this.attach = attach;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getSandbox() {
		return sandbox;
	}

	public void setSandbox(Integer sandbox) {
		this.sandbox = sandbox;
	}

	public String getCreateat() {
		return createat;
	}

	public void setCreateat(String createat) {
		this.createat = createat;
	}

	public String getStartat() {
		return startat;
	}

	public void setStartat(String startat) {
		this.startat = startat;
	}

	public String getPayat() {
		return payat;
	}

	public void setPayat(String payat) {
		this.payat = payat;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
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

	public Integer getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ApplePay [id=");
		builder.append(id);
		builder.append(", orderid=");
		builder.append(orderid);
		builder.append(", transid=");
		builder.append(transid);
		builder.append(", channel=");
		builder.append(channel);
		builder.append(", appid=");
		builder.append(appid);
		builder.append(", ordername=");
		builder.append(ordername);
		builder.append(", price=");
		builder.append(price);
		builder.append(", userid=");
		builder.append(userid);
		builder.append(", username=");
		builder.append(username);
		builder.append(", attach=");
		builder.append(attach);
		builder.append(", status=");
		builder.append(status);
		builder.append(", sandbox=");
		builder.append(sandbox);
		builder.append(", createat=");
		builder.append(createat);
		builder.append(", startat=");
		builder.append(startat);
		builder.append(", payat=");
		builder.append(payat);
		builder.append(", sign=");
		builder.append(sign);
		builder.append(", createTime=");
		builder.append(createTime);
		builder.append(", updateTime=");
		builder.append(updateTime);
		builder.append(", delFlag=");
		builder.append(delFlag);
		builder.append("]");
		return builder.toString();
	}

}
