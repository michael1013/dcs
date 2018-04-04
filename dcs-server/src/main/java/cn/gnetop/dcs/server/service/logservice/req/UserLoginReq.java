package cn.gnetop.dcs.server.service.logservice.req;

import cn.gnetop.dcs.system.entity.Request;
import cn.gnetop.dcs.system.helper.DcsComment;

@DcsComment(comment = "用户登录")
public class UserLoginReq extends Request {

	private static final long serialVersionUID = 7401457600258636659L;

	@DcsComment(comment = "用户id", value = "123456")
	private String userid;
	@DcsComment(comment = "用户名", value = "abc123")
	private String username;
	@DcsComment(comment = "昵称", value = "abc123")
	private String nickname;
	@DcsComment(comment = "ip", value = "127.127.127.127")
	private String ip;
	@DcsComment(comment = "服务器id", value = "123456")
	private String serverid;
	@DcsComment(comment = "创建时间", value = "2017-01-01 00:00:00")
	private String createTime;

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

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getServerid() {
		return serverid;
	}

	public void setServerid(String serverid) {
		this.serverid = serverid;
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
		builder.append(", nickname=");
		builder.append(nickname);
		builder.append(", ip=");
		builder.append(ip);
		builder.append(", serverid=");
		builder.append(serverid);
		builder.append(", createTime=");
		builder.append(createTime);
		builder.append("]");
		return builder.toString();
	}

}
