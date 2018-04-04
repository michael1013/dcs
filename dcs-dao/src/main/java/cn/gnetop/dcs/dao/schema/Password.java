package cn.gnetop.dcs.dao.schema;

import org.apache.ibatis.type.Alias;

import cn.gnetop.dcs.dao.base.DataEntity;

@Alias("Password")
public class Password extends DataEntity {

	private static final long serialVersionUID = 4994310467977010375L;

	private String id;
	private String userid;
	private String pwd;
	private String past;
	private String createTime;
	private String updateTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getPast() {
		return past;
	}

	public void setPast(String past) {
		this.past = past;
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
		builder.append("Password [id=");
		builder.append(id);
		builder.append(", userid=");
		builder.append(userid);
		builder.append(", pwd=");
		builder.append(pwd);
		builder.append(", past=");
		builder.append(past);
		builder.append(", createTime=");
		builder.append(createTime);
		builder.append(", updateTime=");
		builder.append(updateTime);
		builder.append("]");
		return builder.toString();
	}

}
