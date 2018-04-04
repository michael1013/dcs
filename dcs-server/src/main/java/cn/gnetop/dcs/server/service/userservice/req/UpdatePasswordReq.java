package cn.gnetop.dcs.server.service.userservice.req;

import cn.gnetop.dcs.system.entity.Request;
import cn.gnetop.dcs.system.helper.DcsComment;

@DcsComment(comment = "修改密码接口")
public class UpdatePasswordReq extends Request {

	private static final long serialVersionUID = 5373949612614143195L;

	@DcsComment(comment = "用户id", value = "123456")
	private String userid;
	@DcsComment(comment = "用户名", value = "abc123")
	private String username;
	@DcsComment(comment = "旧密码", value = "abc123")
	private String oldPwd;
	@DcsComment(comment = "新密码", value = "abc123")
	private String newPwd;
	@DcsComment(comment = "登录token", value = "abc123")
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

	public String getOldPwd() {
		return oldPwd;
	}

	public void setOldPwd(String oldPwd) {
		this.oldPwd = oldPwd;
	}

	public String getNewPwd() {
		return newPwd;
	}

	public void setNewPwd(String newPwd) {
		this.newPwd = newPwd;
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
		builder.append("UpdatePasswordReq [userid=");
		builder.append(userid);
		builder.append(", username=");
		builder.append(username);
		builder.append(", oldPwd=");
		builder.append(oldPwd);
		builder.append(", newPwd=");
		builder.append(newPwd);
		builder.append(", token=");
		builder.append(token);
		builder.append("]");
		return builder.toString();
	}

}
