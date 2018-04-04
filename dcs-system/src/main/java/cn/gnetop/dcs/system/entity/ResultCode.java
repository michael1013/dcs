package cn.gnetop.dcs.system.entity;

public interface ResultCode {
	// 系统类错误码
	String SUCCESS = "000000";
	String SYS_ERR = "999999";
	String ACCESS_DENY = "403999";
	String TOKEN_INVALID = "403888";
	String PARAM_TEMPER = "402999";
	// 参数类错误码
	String PARAM_ERR = "539999";
	String USERNAME_ERROR = "539998";
	String PASSWORD_ERROR = "539996";
	String PARAM_FORMAT_ERR = "539990";
	String PHONENUM_ERROR = "539984";
	String PARAM_SERVERID_ERR = "539880";
	String TRANS_VERIFY_FAILED = "509999";
	String VERSION_VERIFY_FAILED = "519999";
	// 提示类错误码
	String PASSWORD_WRONG = "538888";
	String USERNAME_MUST_STARTS_BY_LETTER = "538887";
	String OLDPASSWORD_WRONG = "538886";
	// 参数对象错误码
	String USER_NOT_EXISTS = "609999";

	String MOBILE_ALREADY_BIND = "659995";
	String USER_ALREADY_EXISTS = "659999";

	String USERNAME_IS_ILLIGAL = "659994";
	String REGIST_ERR = "659993";

}
