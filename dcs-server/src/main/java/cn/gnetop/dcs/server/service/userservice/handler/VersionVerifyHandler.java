package cn.gnetop.dcs.server.service.userservice.handler;

import java.math.BigDecimal;

import cn.gnetop.dcs.dao.AppVersionDao;
import cn.gnetop.dcs.dao.schema.AppVersion;
import cn.gnetop.dcs.server.convertor.AppVersionConvertor;
import cn.gnetop.dcs.server.service.userservice.req.VersionVerifyReq;
import cn.gnetop.dcs.server.service.userservice.rsp.VersionVerifyRsp;
import cn.gnetop.dcs.system.entity.ResultCode;
import cn.gnetop.dcs.system.exception.DcsException;
import cn.gnetop.dcs.system.helper.ConfigManager;
import cn.gnetop.dcs.system.listener.BeanUtils;

public class VersionVerifyHandler {

	private static final String VERSION = ConfigManager.getConfig("sdk_version", "1.0");

	public VersionVerifyRsp versionVerify(VersionVerifyReq req) throws DcsException {
		String version = req.getVersion();

		this.versionVerify(version);

		AppVersionDao versionDao = BeanUtils.getBean(AppVersionDao.class);
		AppVersion v = new AppVersion();
		v.setBundleid(req.getAppBundleid());
		v.setVersion(req.getAppVersion());
		v = versionDao.findOne(v);
		if (null == v) {
			v = new AppVersion();
			v.setChannel("3");
		}

		VersionVerifyRsp rsp = new VersionVerifyRsp();
		rsp.putData("appVersion", AppVersionConvertor.convertor(v));
		return rsp;
	}

	private void versionVerify(String version) throws DcsException {
		try {
			BigDecimal vb = new BigDecimal(VERSION);
			BigDecimal v = new BigDecimal(version);
			if (vb.compareTo(v) > 0) {
				throw new DcsException(ResultCode.VERSION_VERIFY_FAILED);
			}
		} catch (Exception e) {
			throw new DcsException(ResultCode.VERSION_VERIFY_FAILED);
		}
	}

}
