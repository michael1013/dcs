package cn.gnetop.dcs.server.service.userservice.req;

import cn.gnetop.dcs.system.entity.Request;
import cn.gnetop.dcs.system.helper.DcsComment;

@DcsComment(comment = "用户注册接口")
public class UserRegistReq extends Request {

	private static final long serialVersionUID = 3020412574034514904L;
	@DcsComment(comment = "用户名", value = "abc123")
	private String username;
	@DcsComment(comment = "密码", value = "abc123")
	private String password;
	@DcsComment(comment = "手机号", value = "13900001111")
	private String mobile;
	@DcsComment(comment = "设备token", value = "abc123")
	private String deviceToken;
	@DcsComment(comment = "登录地址纬度", value = "12.3456")
	private Double lat;
	@DcsComment(comment = "登录地址经度", value = "123.456")
	private Double lng;
	@DcsComment(comment = "注册类型", value = "account/random/phone")
	private String registType;

	public String getRegistType() {
		return registType;
	}

	public void setRegistType(String registType) {
		this.registType = registType;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDeviceToken() {
		return deviceToken;
	}

	public void setDeviceToken(String deviceToken) {
		this.deviceToken = deviceToken;
	}

	public Double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

	public Double getLng() {
		return lng;
	}

	public void setLng(Double lng) {
		this.lng = lng;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UserRegistReq [username=");
		builder.append(username);
		builder.append(", password=");
		builder.append(password);
		builder.append(", deviceToken=");
		builder.append(deviceToken);
		builder.append(", lat=");
		builder.append(lat);
		builder.append(", lng=");
		builder.append(lng);
		builder.append("]");
		return builder.toString();
	}

}
