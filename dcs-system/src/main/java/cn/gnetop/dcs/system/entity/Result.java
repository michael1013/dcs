package cn.gnetop.dcs.system.entity;

import java.io.Serializable;

public class Result implements Serializable {

	private static final long serialVersionUID = 7047966424694182683L;

	private String resultCode;

	private String resultMsg;

	public Result(String resultCode, String resultMsg) {
		super();
		this.resultCode = resultCode;
		this.resultMsg = resultMsg;
	}

	public Result() {
		super();
	}

	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public String getResultMsg() {
		return resultMsg;
	}

	public void setResultMsg(String resultMsg) {
		this.resultMsg = resultMsg;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Result [resultCode=");
		builder.append(resultCode);
		builder.append(", resultMsg=");
		builder.append(resultMsg);
		builder.append("]");
		return builder.toString();
	}

}
