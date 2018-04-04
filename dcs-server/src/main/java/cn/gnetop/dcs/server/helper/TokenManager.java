package cn.gnetop.dcs.server.helper;

import java.text.ParseException;
import java.util.Date;

import cn.gnetop.pde.foundation.DateUtils;
import cn.gnetop.pde.foundation.StringUtils;

public class TokenManager {

	private static final long TOKEN_EFFECT_TIME = 1000 * 3600 * 24 * 7;

	public static boolean verifyToken(String token, String tokenDb, String tokenDate) {
		if (StringUtils.isNotBlank(tokenDb, tokenDate)) {
			Date tokenD;
			try {
				tokenD = DateUtils.parseDate(tokenDate, DateUtils.COMMON_FORMATE_SQL);
			} catch (ParseException e) {
				e.printStackTrace();
				return false;
			}
			if (tokenDb.equals(token)) {
				long tokenTime = tokenD.getTime();
				long nowTime = System.currentTimeMillis();
				if (nowTime - tokenTime < TOKEN_EFFECT_TIME) {
					return true;
				}
			}
		}
		return false;
	}

}
