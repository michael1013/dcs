package cn.gnetop.dcs.dao.schema;

import cn.gnetop.dcs.dao.base.DataEntity;

public class SecurityInfo extends DataEntity {

	private static final long serialVersionUID = -1238815852959391167L;

	private String id;
	private String userid;

	private String answer1;
	private String answer2;
	private String answer3;

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

	public String getAnswer1() {
		return answer1;
	}

	public void setAnswer1(String answer1) {
		this.answer1 = answer1;
	}

	public String getAnswer2() {
		return answer2;
	}

	public void setAnswer2(String answer2) {
		this.answer2 = answer2;
	}

	public String getAnswer3() {
		return answer3;
	}

	public void setAnswer3(String answer3) {
		this.answer3 = answer3;
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
		builder.append("SecurityInfo [id=");
		builder.append(id);
		builder.append(", userid=");
		builder.append(userid);
		builder.append(", answer1=");
		builder.append(answer1);
		builder.append(", answer2=");
		builder.append(answer2);
		builder.append(", answer3=");
		builder.append(answer3);
		builder.append(", createTime=");
		builder.append(createTime);
		builder.append(", updateTime=");
		builder.append(updateTime);
		builder.append("]");
		return builder.toString();
	}

}
