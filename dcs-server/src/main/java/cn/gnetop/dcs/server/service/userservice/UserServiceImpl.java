package cn.gnetop.dcs.server.service.userservice;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.gnetop.dcs.server.service.userservice.handler.BindMobileHandler;
import cn.gnetop.dcs.server.service.userservice.handler.GetApplePayHandler;
import cn.gnetop.dcs.server.service.userservice.handler.GetUserConsumableHandler;
import cn.gnetop.dcs.server.service.userservice.handler.GetUserInfoHandler;
import cn.gnetop.dcs.server.service.userservice.handler.LogApplePayHandler;
import cn.gnetop.dcs.server.service.userservice.handler.ResetPasswordHandler;
import cn.gnetop.dcs.server.service.userservice.handler.UpdatePasswordHandler;
import cn.gnetop.dcs.server.service.userservice.handler.UpdateUserInfoHandler;
import cn.gnetop.dcs.server.service.userservice.handler.UserLoginHandler;
import cn.gnetop.dcs.server.service.userservice.handler.UserRegistHandler;
import cn.gnetop.dcs.server.service.userservice.handler.VerifyReceiptHandler;
import cn.gnetop.dcs.server.service.userservice.handler.VerifyTokenHandler;
import cn.gnetop.dcs.server.service.userservice.handler.VersionVerifyHandler;
import cn.gnetop.dcs.server.service.userservice.req.BindMobileReq;
import cn.gnetop.dcs.server.service.userservice.req.GetApplePayReq;
import cn.gnetop.dcs.server.service.userservice.req.GetUserConsumableReq;
import cn.gnetop.dcs.server.service.userservice.req.GetUserInfoReq;
import cn.gnetop.dcs.server.service.userservice.req.LogApplePayReq;
import cn.gnetop.dcs.server.service.userservice.req.ResetPasswordReq;
import cn.gnetop.dcs.server.service.userservice.req.UpdatePasswordReq;
import cn.gnetop.dcs.server.service.userservice.req.UpdateUserInfoReq;
import cn.gnetop.dcs.server.service.userservice.req.UserLoginReq;
import cn.gnetop.dcs.server.service.userservice.req.UserRegistReq;
import cn.gnetop.dcs.server.service.userservice.req.VerifyReceiptReq;
import cn.gnetop.dcs.server.service.userservice.req.VerifyTokenReq;
import cn.gnetop.dcs.server.service.userservice.req.VersionVerifyReq;
import cn.gnetop.dcs.server.service.userservice.rsp.BindMobileRsp;
import cn.gnetop.dcs.server.service.userservice.rsp.GetApplePayRsp;
import cn.gnetop.dcs.server.service.userservice.rsp.GetUserConsumableRsp;
import cn.gnetop.dcs.server.service.userservice.rsp.GetUserInfoRsp;
import cn.gnetop.dcs.server.service.userservice.rsp.LogApplePayRsp;
import cn.gnetop.dcs.server.service.userservice.rsp.ResetPasswordRsp;
import cn.gnetop.dcs.server.service.userservice.rsp.UpdatePasswordRsp;
import cn.gnetop.dcs.server.service.userservice.rsp.UpdateUserInfoRsp;
import cn.gnetop.dcs.server.service.userservice.rsp.UserLoginRsp;
import cn.gnetop.dcs.server.service.userservice.rsp.UserRegistRsp;
import cn.gnetop.dcs.server.service.userservice.rsp.VerifyReceiptRsp;
import cn.gnetop.dcs.server.service.userservice.rsp.VerifyTokenRsp;
import cn.gnetop.dcs.server.service.userservice.rsp.VersionVerifyRsp;
import cn.gnetop.dcs.system.exception.DcsException;

@Transactional
@Service("userService")
public class UserServiceImpl implements UserService {

	@Override
	public UserLoginRsp userLogin(UserLoginReq req) throws DcsException {
		return new UserLoginHandler().userLogin(req);
	}

	@Override
	public UserRegistRsp userRegist(UserRegistReq req) throws DcsException {
		return new UserRegistHandler().userRegist(req);
	}

	@Override
	public GetUserInfoRsp getUserInfo(GetUserInfoReq req) throws DcsException {
		return new GetUserInfoHandler().getUserInfo(req);
	}

	@Override
	public UpdateUserInfoRsp updateUserInfo(UpdateUserInfoReq req) throws DcsException {
		return new UpdateUserInfoHandler().updateUserInfo(req);
	}

	@Override
	public UpdatePasswordRsp updatePassword(UpdatePasswordReq req) throws DcsException {
		return new UpdatePasswordHandler().updatePassword(req);
	}

	@Override
	public BindMobileRsp bindMobile(BindMobileReq req) throws DcsException {
		return new BindMobileHandler().bindMobile(req);
	}

	@Override
	public ResetPasswordRsp resetPassword(ResetPasswordReq req) throws DcsException {
		return new ResetPasswordHandler().resetPassword(req);
	}

	@Override
	public VerifyTokenRsp verifyToken(VerifyTokenReq req) throws DcsException {
		return new VerifyTokenHandler().verifyToken(req);
	}

	@Override
	public LogApplePayRsp logApplePay(LogApplePayReq req) throws DcsException {
		return new LogApplePayHandler().logApplePay(req);
	}

	@Override
	public GetApplePayRsp getApplePay(GetApplePayReq req) throws DcsException {
		return new GetApplePayHandler().getApplePay(req);
	}

	@Override
	public VerifyReceiptRsp verifyReceipt(VerifyReceiptReq req) throws DcsException {
		return new VerifyReceiptHandler().verifyReceipt(req);
	}

	@Override
	public VersionVerifyRsp versionVerify(VersionVerifyReq req) throws DcsException {
		return new VersionVerifyHandler().versionVerify(req);
	}

	@Override
	public GetUserConsumableRsp getUserConsumable(GetUserConsumableReq req) {
		return new GetUserConsumableHandler().getUserConsumable(req);
	}

}
