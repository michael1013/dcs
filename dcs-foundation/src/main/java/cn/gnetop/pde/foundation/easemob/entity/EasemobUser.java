package cn.gnetop.pde.foundation.easemob.entity;

import java.io.Serializable;

public class EasemobUser implements Serializable {

	private static final long serialVersionUID = -2092167709644257145L;

	private String username;
	private String password;
	private String nickname;
	private String userid;
	private String icon;
	private String newpassword;
	private Integer lv;

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
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

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getNewpassword() {
		return newpassword;
	}

	public void setNewpassword(String newpassword) {
		this.newpassword = newpassword;
	}

	public Integer getLv() {
		return lv;
	}

	public void setLv(Integer lv) {
		this.lv = lv;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("EasemobUser [username=");
		builder.append(username);
		builder.append(", password=");
		builder.append(password);
		builder.append(", nickname=");
		builder.append(nickname);
		builder.append(", userid=");
		builder.append(userid);
		builder.append(", icon=");
		builder.append(icon);
		builder.append(", newpassword=");
		builder.append(newpassword);
		builder.append(", lv=");
		builder.append(lv);
		builder.append("]");
		return builder.toString();
	}

}
