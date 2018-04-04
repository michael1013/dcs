package cn.gnetop.dcs.server.service.userservice.req;

import cn.gnetop.dcs.system.entity.Request;
import cn.gnetop.dcs.system.helper.DcsComment;

@DcsComment(comment = "修改用户信息接口")
public class UpdateUserInfoReq extends Request {

	private static final long serialVersionUID = 3527716547602022699L;

	@DcsComment(comment = "用户id", value = "123456")
	private String userid;
	@DcsComment(comment = "用户名", value = "123456")
	private String username;
	@DcsComment(comment = "昵称", value = "abc123")
	private String nickname;
	@DcsComment(comment = "登录token", value = "abc123")
	private String token;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
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

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UpdateUserInfoReq [userid=");
		builder.append(userid);
		builder.append(", username=");
		builder.append(username);
		builder.append(", nickname=");
		builder.append(nickname);
		builder.append("]");
		return builder.toString();
	}

}
