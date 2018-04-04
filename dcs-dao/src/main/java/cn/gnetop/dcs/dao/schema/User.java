package cn.gnetop.dcs.dao.schema;

import org.apache.ibatis.type.Alias;

import cn.gnetop.dcs.dao.base.DataEntity;
import cn.gnetop.dcs.dao.provider.Column;
import cn.gnetop.dcs.dao.provider.GeneratedKeys;

@Alias("User")
public class User extends DataEntity {

	private static final long serialVersionUID = 3856345357241268056L;

	@Column
	@GeneratedKeys
	private String userid;
	@Column
	private String gameid;
	@Column
	private String username;
	@Column
	private String serverid;

	private String password;
	@Column
	private String nickname;
	@Column
	private String mobile;

	private String email;
	@Column
	private Double lat;
	@Column
	private Double lng;
	@Column
	private String deviceToken;
	@Column
	private String token;
	@Column
	private String tokenDate;
	@Column
	private String lastLoginTime;
	@Column
	private String createTime;
	@Column
	private String updateTime;
	@Column
	private Boolean isGuest;

	public Boolean getIsGuest() {
		return isGuest;
	}

	public void setIsGuest(Boolean isGuest) {
		this.isGuest = isGuest;
	}

	public String getGameid() {
		return gameid;
	}

	public void setGameid(String gameid) {
		this.gameid = gameid;
	}

	public String getServerid() {
		return serverid;
	}

	public void setServerid(String serverid) {
		this.serverid = serverid;
	}

	public String getTokenDate() {
		return tokenDate;
	}

	public void setTokenDate(String tokenDate) {
		this.tokenDate = tokenDate;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getDeviceToken() {
		return deviceToken;
	}

	public void setDeviceToken(String deviceToken) {
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

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(String lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("User [userid=");
		builder.append(userid);
		builder.append(", gameid=");
		builder.append(gameid);
		builder.append(", username=");
		builder.append(username);
		builder.append(", serverid=");
		builder.append(serverid);
		builder.append(", password=");
		builder.append(password);
		builder.append(", nickname=");
		builder.append(nickname);
		builder.append(", mobile=");
		builder.append(mobile);
		builder.append(", email=");
		builder.append(email);
		builder.append(", lat=");
		builder.append(lat);
		builder.append(", lng=");
		builder.append(lng);
		builder.append(", deviceToken=");
		builder.append(deviceToken);
		builder.append(", token=");
		builder.append(token);
		builder.append(", tokenDate=");
		builder.append(tokenDate);
		builder.append(", lastLoginTime=");
		builder.append(lastLoginTime);
		builder.append(", createTime=");
		builder.append(createTime);
		builder.append(", updateTime=");
		builder.append(updateTime);
		builder.append(", isGuest=");
		builder.append(isGuest);
		builder.append("]");
		return builder.toString();
	}

}
