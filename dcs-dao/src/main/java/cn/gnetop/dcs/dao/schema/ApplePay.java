package cn.gnetop.dcs.dao.schema;

import cn.gnetop.dcs.dao.base.DataEntity;
import cn.gnetop.dcs.dao.provider.Column;

public class ApplePay extends DataEntity {

	private static final long serialVersionUID = 3110564771625980585L;

	@Column
	private String id;
	@Column
	private String transid;
	@Column
	private String orderid;
	@Column
	private String channel;
	@Column
	private String appid;
	@Column
	private String bundleid;
	@Column
	private String productid;
	@Column
	private Integer consumable;
	@Column
	private String productname;
	@Column
	private String price;
	@Column
	private String currency;
	@Column
	private String userid;
	@Column
	private String username;
	@Column
	private String gameServerId;
	@Column
	private String status;
	@Column
	private Integer sandbox;
	@Column
	private String createat;
	@Column
	private String sign;
	@Column
	private String deliveryFlag;
	@Column
	private String shippingUrl;
	@Column
	private String receipt;
	@Column
	private String extra;
	@Column
	private String createTime;
	@Column
	private String updateTime;
	@Column
	private Integer delFlag;

	public String getExtra() {
		return extra;
	}

	public void setExtra(String extra) {
		this.extra = extra;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getShippingUrl() {
		return shippingUrl;
	}

	public void setShippingUrl(String shippingUrl) {
		this.shippingUrl = shippingUrl;
	}

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	public Integer getConsumable() {
		return consumable;
	}

	public void setConsumable(Integer consumable) {
		this.consumable = consumable;
	}

	public String getGameServerId() {
		return gameServerId;
	}

	public void setGameServerId(String gameServerId) {
		this.gameServerId = gameServerId;
	}

	public String getDeliveryFlag() {
		return deliveryFlag;
	}

	public void setDeliveryFlag(String deliveryFlag) {
		this.deliveryFlag = deliveryFlag;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getBundleid() {
		return bundleid;
	}

	public void setBundleid(String bundleid) {
		this.bundleid = bundleid;
	}

	public String getProductid() {
		return productid;
	}

	public void setProductid(String productid) {
		this.productid = productid;
	}

	public String getProductname() {
		return productname;
	}

	public void setProductname(String productname) {
		this.productname = productname;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
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

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getReceipt() {
		return receipt;
	}

	public void setReceipt(String receipt) {
		this.receipt = receipt;
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
		builder.append(", transid=");
		builder.append(transid);
		builder.append(", channel=");
		builder.append(channel);
		builder.append(", appid=");
		builder.append(appid);
		builder.append(", bundleid=");
		builder.append(bundleid);
		builder.append(", productid=");
		builder.append(productid);
		builder.append(", consumable=");
		builder.append(consumable);
		builder.append(", productname=");
		builder.append(productname);
		builder.append(", price=");
		builder.append(price);
		builder.append(", userid=");
		builder.append(userid);
		builder.append(", username=");
		builder.append(username);
		builder.append(", status=");
		builder.append(status);
		builder.append(", sandbox=");
		builder.append(sandbox);
		builder.append(", createat=");
		builder.append(createat);
		builder.append(", sign=");
		builder.append(sign);
		builder.append(", receipt=");
		builder.append(receipt);
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
