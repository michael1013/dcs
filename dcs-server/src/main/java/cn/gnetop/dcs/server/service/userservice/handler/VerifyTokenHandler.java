package cn.gnetop.dcs.server.service.userservice.handler;

import org.apache.commons.lang3.StringUtils;

import cn.gnetop.dcs.dao.UserDao;
import cn.gnetop.dcs.dao.schema.User;
import cn.gnetop.dcs.server.service.userservice.req.VerifyTokenReq;
import cn.gnetop.dcs.server.service.userservice.rsp.VerifyTokenRsp;
import cn.gnetop.dcs.system.entity.ResultCode;
import cn.gnetop.dcs.system.exception.DcsException;
import cn.gnetop.dcs.system.exception.ParamErrException;
import cn.gnetop.dcs.system.listener.BeanUtils;
import net.sf.json.JSONObject;

public class VerifyTokenHandler {

	private UserDao userDao = BeanUtils.getBean(UserDao.class);

	public VerifyTokenRsp verifyToken(VerifyTokenReq req) throws DcsException {
		String token = req.getToken();
		if (StringUtils.isBlank(token)) {
			throw new ParamErrException(ResultCode.PARAM_ERR);
		}
		User user = new User();
		user.setToken(token);
		user = userDao.findOne(user);
		VerifyTokenRsp rsp = new VerifyTokenRsp();
		JSONObject j = new JSONObject();
		if (null != user) {
			String userid = user.getUserid();
			j.put("userid", userid);
		} else {
			throw new ParamErrException(ResultCode.TOKEN_INVALID);
		}
		rsp.setData(j);
		return rsp;
	}
}
