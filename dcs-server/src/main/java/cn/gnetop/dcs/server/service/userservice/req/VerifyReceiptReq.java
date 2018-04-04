package cn.gnetop.dcs.server.service.userservice.req;

import cn.gnetop.dcs.system.entity.Request;

public class VerifyReceiptReq extends Request {

	private static final long serialVersionUID = 1199275890700372435L;

	private String transid;
	private String userid;
	private String username;
	private String appid;
	private String channel;
	private String productname;
	private String price;
	private String currency;
	private Integer sandbox;
	private String receiptData;
	private Integer consumable;

	private String password;

	private String shippingUrl;
	private String gameServerId;

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getTransid() {
		return transid;
	}

	public void setTransid(String transid) {
		this.transid = transid;
	}

	public String getShippingUrl() {
		return shippingUrl;
	}

	public void setShippingUrl(String shippingUrl) {
		this.shippingUrl = shippingUrl;
	}

	public String getGameServerId() {
		return gameServerId;
	}

	public void setGameServerId(String gameServerId) {
		this.gameServerId = gameServerId;
	}

	public Integer getSandbox() {
		return sandbox;
	}

	public void setSandbox(Integer sandbox) {
		this.sandbox = sandbox;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getProductname() {
		return productname;
	}

	public void setProductname(String productname) {
		this.productname = productname;
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

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getReceiptData() {
		return receiptData;
	}

	public void setReceiptData(String receiptData) {
		this.receiptData = receiptData;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public Integer getConsumable() {
		return consumable;
	}

	public void setConsumable(Integer consumable) {
		this.consumable = consumable;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("VerifyReceiptReq [userid=");
		builder.append(userid);
		builder.append(", username=");
		builder.append(username);
		builder.append(", appid=");
		builder.append(appid);
		builder.append(", channel=");
		builder.append(channel);
		builder.append(", productname=");
		builder.append(productname);
		builder.append(", price=");
		builder.append(price);
		builder.append(", sandbox=");
		builder.append(sandbox);
		builder.append(", receiptData=");
		builder.append(receiptData);
		builder.append(", consumable=");
		builder.append(consumable);
		builder.append(", password=");
		builder.append(password);
		builder.append(", toString()=");
		builder.append(super.toString());
		builder.append("]");
		return builder.toString();
	}

}
