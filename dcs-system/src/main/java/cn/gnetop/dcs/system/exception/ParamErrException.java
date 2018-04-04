package cn.gnetop.dcs.system.exception;

public class ParamErrException extends DcsException {

	private static final long serialVersionUID = -1283683275215739298L;

	public ParamErrException() {
	}
	
	public ParamErrException(String resultCode) {
		super(resultCode);
	}

	public ParamErrException(String resultCode, Throwable e) {
		super(resultCode, e);
	}

}
