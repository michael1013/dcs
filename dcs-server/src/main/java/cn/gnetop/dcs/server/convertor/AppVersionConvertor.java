package cn.gnetop.dcs.server.convertor;

import java.util.Arrays;

import cn.gnetop.dcs.dao.schema.AppVersion;

public class AppVersionConvertor {

	public static cn.gnetop.dcs.server.service.userservice.schema.AppVersion convertor(AppVersion v) {
		if (null != v) {
			cn.gnetop.dcs.server.service.userservice.schema.AppVersion version = new cn.gnetop.dcs.server.service.userservice.schema.AppVersion();
			version.setId(v.getId());
			version.setBundleid(v.getBundleid());
			version.setVersion(v.getVersion());
			if (null != v.getChannel()) {
				version.setChannel(Arrays.asList(v.getChannel().split(",")));
			}
			return version;
		}
		return null;
	}

}
