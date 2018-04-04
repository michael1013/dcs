package cn.gnetop.dcs.system.entity;

import java.io.Serializable;

public class Request implements Serializable {

	private static final long serialVersionUID = 4318959300549918819L;

	private String extraInfo;

	public String getExtraInfo() {
		return extraInfo;
	}

	public void setExtraInfo(String extraInfo) {
		this.extraInfo = extraInfo;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Request [extraInfo=");
		builder.append(extraInfo);
		builder.append("]");
		return builder.toString();
	}

}
