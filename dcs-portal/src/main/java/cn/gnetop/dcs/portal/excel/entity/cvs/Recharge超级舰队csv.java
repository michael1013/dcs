package cn.gnetop.dcs.portal.excel.entity.cvs;

import java.text.ParseException;

import org.apache.commons.lang3.StringUtils;

import cn.gnetop.dcs.portal.excel.entity.CommonRecharge;
import cn.gnetop.pde.foundation.DateUtils;
import cn.gnetop.pde.foundation.encryte.MD5Utils;

public class Recharge超级舰队csv extends CommonRecharge {

	private static final long serialVersionUID = -3355539330629548062L;

	public Recharge超级舰队csv(String gameid, String value, String file) {
		if (StringUtils.isNoneBlank(value)) {
			value = value.replaceAll("\\\"", "");
		}
		String[] column = value.split("\\s+|,");
		if (column.length == 7) {
			this.gameid = gameid;
			this.file = file;
			this.userid = column[0];
			// this.username = column[4];
			this.orderid = column[2];
			try {
				this.logTime = DateUtils.formate(DateUtils.parseDate(column[4] + ' ' + column[5], "yyyy-MM-dd HH:mm:ss",
				        "yyyy/MM/dd HH:mm:ss", "yyyy-MM-dd HH:mm:ss.SSSSSSSSS"));
			} catch (ParseException e) {
				System.err.println(this.file);
				e.printStackTrace();
			}
			this.ip = column[6];
			this.amount = column[3];
			this.currency = "CNF";
			this.channel = column[1];
			if (StringUtils.isNoneBlank(this.file)) {
				this.serverid = this.gameid + '_' + 1;
			}
			this.createTime = DateUtils.getDateString();
			this.md5 = MD5Utils.encrypt(this.toString());
			this.isValid = true;
		} else {
			this.isValid = false;
		}
	}

}
