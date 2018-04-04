package cn.gnetop.dcs.system.exception;

import org.apache.commons.lang3.StringUtils;

import cn.gnetop.dcs.system.entity.ResultCode;

public class DcsException extends Throwable {

	private static final long serialVersionUID = 5433497164756160645L;

	private String resultCode;

	public DcsException() {
		this.resultCode = ResultCode.SYS_ERR;
	}

	public DcsException(Exception e) {
		super(e);
	}

	public DcsException(String resultCode) {
		super();
		this.resultCode = resultCode;
	}

	public DcsException(String resultCode, Throwable e) {
		super(e);
		this.resultCode = resultCode;
	}

	public DcsException(String resultCode, String errorMsg) {
		super(errorMsg);
		this.resultCode = StringUtils.isBlank(resultCode) ? ResultCode.SYS_ERR : resultCode;
	}

	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DcsException [resultCode=");
		builder.append(resultCode);
		builder.append(", errorMsg=");
		builder.append(super.getMessage());
		builder.append("]");
		return builder.toString();
	}

}
