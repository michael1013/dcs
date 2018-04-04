package cn.gnetop.pde.foundation.easemob.entity;

import org.apache.commons.lang3.StringUtils;

public class EasemobException extends Exception {

	private static final long serialVersionUID = -3484772618570680635L;

	private String error;
	private String exception;
	private String error_description;

	public EasemobException() {
		super();
	}
	
	public EasemobException(Throwable cause) {
        super(cause);
    }

	public EasemobException(EasemobEntity<?> e) {
		super();
		if (null != e) {
			if (StringUtils.isNotBlank(e.getException())) {
				this.error = e.getError();
				this.error_description = e.getError_description();
				this.exception = e.getException();
			}
		} else {
			this.error_description = "entity is null";
		}
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getException() {
		return exception;
	}

	public void setException(String exception) {
		this.exception = exception;
	}

	public String getError_description() {
		return error_description;
	}

	public void setError_description(String error_description) {
		this.error_description = error_description;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("EasemobException [error=");
		builder.append(error);
		builder.append(", exception=");
		builder.append(exception);
		builder.append(", error_description=");
		builder.append(error_description);
		builder.append("]");
		return builder.toString();
	}

}
