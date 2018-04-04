package cn.gnetop.dcs.server.service.userservice;

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
import cn.gnetop.dcs.system.engine.IService;
import cn.gnetop.dcs.system.exception.DcsException;

public interface UserService extends IService {

	GetUserInfoRsp getUserInfo(GetUserInfoReq req) throws DcsException;

	UpdatePasswordRsp updatePassword(UpdatePasswordReq req) throws DcsException;

	UpdateUserInfoRsp updateUserInfo(UpdateUserInfoReq req) throws DcsException;

	UserLoginRsp userLogin(UserLoginReq req) throws DcsException;

	UserRegistRsp userRegist(UserRegistReq req) throws DcsException;

	BindMobileRsp bindMobile(BindMobileReq req) throws DcsException;

	ResetPasswordRsp resetPassword(ResetPasswordReq req) throws DcsException;

	VerifyTokenRsp verifyToken(VerifyTokenReq req) throws DcsException;

	LogApplePayRsp logApplePay(LogApplePayReq req) throws DcsException;

	GetApplePayRsp getApplePay(GetApplePayReq req) throws DcsException;

	VerifyReceiptRsp verifyReceipt(VerifyReceiptReq req) throws DcsException;

	VersionVerifyRsp versionVerify(VersionVerifyReq req) throws DcsException;

	GetUserConsumableRsp getUserConsumable(GetUserConsumableReq req);
}
