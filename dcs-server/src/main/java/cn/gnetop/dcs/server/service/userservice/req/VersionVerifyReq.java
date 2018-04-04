package cn.gnetop.dcs.server.service.userservice.req;

import cn.gnetop.dcs.system.entity.Request;

public class VersionVerifyReq extends Request {

	private static final long serialVersionUID = 2604158388735542767L;

	private String version;

	private String appVersion;
	private String appBundleid;

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getAppVersion() {
		return appVersion;
	}

	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}

	public String getAppBundleid() {
		return appBundleid;
	}

	public void setAppBundleid(String appBundleid) {
		this.appBundleid = appBundleid;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("VersionVerifyReq [version=");
		builder.append(version);
		builder.append(", appVersion=");
		builder.append(appVersion);
		builder.append(", appBundleid=");
		builder.append(appBundleid);
		builder.append("]");
		return builder.toString();
	}

}
