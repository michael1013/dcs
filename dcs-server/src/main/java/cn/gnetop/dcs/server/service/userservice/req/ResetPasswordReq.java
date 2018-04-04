package cn.gnetop.dcs.server.service.userservice.req;

import cn.gnetop.dcs.system.entity.Request;
import cn.gnetop.dcs.system.helper.DcsComment;

@DcsComment(comment = "重置密码接口")
public class ResetPasswordReq extends Request {

	private static final long serialVersionUID = -7549206991060470269L;

	@DcsComment(comment = "手机号", value = "13900001111")
	private String mobile;
	@DcsComment(comment = "新密码", value = "abc123")
	private String newPwd;

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getNewPwd() {
		return newPwd;
	}

	public void setNewPwd(String newPwd) {
		this.newPwd = newPwd;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ResetPasswordReq [mobile=");
		builder.append(mobile);
		builder.append(", newPwd=");
		builder.append(newPwd);
		builder.append("]");
		return builder.toString();
	}

}
