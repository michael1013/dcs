package cn.gnetop.dcs.dao.schema;

import cn.gnetop.dcs.dao.base.DataEntity;

public class FileHistory extends DataEntity {

	private static final long serialVersionUID = 4991252800181309861L;

	private String id;
	private String path;
	private String md5;
	private String createTime;
	private String account;
	private String serverid;
	private String dateTime;
	private Boolean isFinish;

	public Boolean getIsFinish() {
		return isFinish;
	}

	public void setIsFinish(Boolean isFinish) {
		this.isFinish = isFinish;
	}

	public FileHistory() {
		super();
	}

	public FileHistory(String path, String md5, String createTime) {
		super();
		this.path = path;
		this.md5 = md5;
		this.createTime = createTime;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getMd5() {
		return md5;
	}

	public void setMd5(String md5) {
		this.md5 = md5;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getServerid() {
		return serverid;
	}

	public void setServerid(String serverid) {
		this.serverid = serverid;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FileHistory [id=");
		builder.append(id);
		builder.append(", path=");
		builder.append(path);
		builder.append(", md5=");
		builder.append(md5);
		builder.append(", createTime=");
		builder.append(createTime);
		builder.append(", account=");
		builder.append(account);
		builder.append(", serverid=");
		builder.append(serverid);
		builder.append(", dateTime=");
		builder.append(dateTime);
		builder.append("]");
		return builder.toString();
	}

}
