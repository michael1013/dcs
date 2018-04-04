package cn.gnetop.dcs.server.service.userservice.req;

import cn.gnetop.dcs.system.entity.Request;
import cn.gnetop.dcs.system.helper.DcsComment;

@DcsComment(comment = "用户登录接口")
public class UserLoginReq extends Request {

	private static final long serialVersionUID = 7401457600258636659L;
	@DcsComment(comment = "用户id", value = "123456")
	private String userid;
	@DcsComment(comment = "用户名", value = "123456")
	private String username;
	@DcsComment(comment = "手机号", value = "13900001111")
	private String mobile;
	@DcsComment(comment = "用户密码", value = "123456")
	private String password;
	@DcsComment(comment = "登录设备ID", value = "ABCD1234")
	private Double deviceToken;
	@DcsComment(comment = "登录位置, 纬度", value = "123.456")
	private Double lat;
	@DcsComment(comment = "登录位置, 经度", value = "123.456")
	private Double lng;
	@DcsComment(comment = "登录时间", value = "2017-01-01 01:01:01")
	private String createTime;

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Double getDeviceToken() {
		return deviceToken;
	}

	public void setDeviceToken(Double deviceToken) {
		this.deviceToken = deviceToken;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UserLoginReq [userid=");
		builder.append(userid);
		builder.append(", username=");
		builder.append(username);
		builder.append(", password=");
		builder.append(password);
		builder.append(", lat=");
		builder.append(lat);
		builder.append(", lng=");
		builder.append(lng);
		builder.append(", createTime=");
		builder.append(createTime);
		builder.append("]");
		return builder.toString();
	}

}
