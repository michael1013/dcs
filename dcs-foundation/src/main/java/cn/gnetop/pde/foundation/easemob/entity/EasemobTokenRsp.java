package cn.gnetop.pde.foundation.easemob.entity;

import java.io.Serializable;

public class EasemobTokenRsp implements Serializable {

	private static final long serialVersionUID = -3699726675748538192L;
	private String access_token;
	private String expires_in;
	private String application;

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	public String getExpires_in() {
		return expires_in;
	}

	public void setExpires_in(String expires_in) {
		this.expires_in = expires_in;
	}

	public String getApplication() {
		return application;
	}

	public void setApplication(String application) {
		this.application = application;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("EasemobTokenRsp [access_token=");
		builder.append(access_token);
		builder.append(", expires_in=");
		builder.append(expires_in);
		builder.append(", application=");
		builder.append(application);
		builder.append("]");
		return builder.toString();
	}

}
