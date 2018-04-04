package cn.gnetop.dcs.dao.schema;

import cn.gnetop.dcs.dao.base.DataEntity;
import cn.gnetop.dcs.dao.provider.Column;

public class LoginLog extends DataEntity {

	private static final long serialVersionUID = 7769845434212209105L;

	@Column
	private String id;
	@Column
	private String userid;
	@Column
	private String username;
	@Column
	private String mobile;
	@Column
	private String device;
	@Column
	private String token;
	@Column
	private String ip;

	@Column
	private String createTime;
	@Column
	private String updateTime;
	@Column
	private Integer delFlag;

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

	public String getDevice() {
		return device;
	}

	public void setDevice(String device) {
		this.device = device;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("LoginLog [userid=");
		builder.append(userid);
		builder.append(", username=");
		builder.append(username);
		builder.append(", mobile=");
		builder.append(mobile);
		builder.append(", device=");
		builder.append(device);
		builder.append(", token=");
		builder.append(token);
		builder.append(", ip=");
		builder.append(ip);
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
