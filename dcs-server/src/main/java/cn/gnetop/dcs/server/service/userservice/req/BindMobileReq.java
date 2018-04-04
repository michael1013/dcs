package cn.gnetop.dcs.server.service.userservice.req;

import cn.gnetop.dcs.system.entity.Request;
import cn.gnetop.dcs.system.helper.DcsComment;

@DcsComment(comment = "绑定手机号接口")
public class BindMobileReq extends Request {

	private static final long serialVersionUID = -8109206610599375338L;

	@DcsComment(comment = "用户id", value = "123456")
	private String userid;
	@DcsComment(comment = "用户名", value = "abc456")
	private String username;
	@DcsComment(comment = "手机号", value = "13900001111")
	private String mobile;
	@DcsComment(comment = "token", value = "abc123")
	private String token;

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

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BindMobileReq [userid=");
		builder.append(userid);
		builder.append(", username=");
		builder.append(username);
		builder.append(", mobile=");
		builder.append(mobile);
		builder.append(", token=");
		builder.append(token);
		builder.append("]");
		return builder.toString();
	}

}
