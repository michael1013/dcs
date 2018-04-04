package cn.gnetop.dcs.portal.excel.entity.cvs;

import java.text.ParseException;

import org.apache.commons.lang3.StringUtils;

import cn.gnetop.dcs.portal.excel.entity.CommonRecharge;
import cn.gnetop.pde.foundation.DateUtils;
import cn.gnetop.pde.foundation.encryte.MD5Utils;

public class Recharge霸王的大陆csv extends CommonRecharge {

	private static final long serialVersionUID = -3355539330629548062L;

	public Recharge霸王的大陆csv(String gameid, String value, String file) {
		if (StringUtils.isNoneBlank(value)) {
			value = value.replaceAll("\\\"", "");
		}
		String[] column = value.split("\\s+|,");
		if (column.length == 25) {
			this.gameid = gameid;
			this.file = file;
			this.userid = column[1];
			// this.username = column[4];
			this.orderid = column[16];
			try {
				this.logTime = DateUtils.formate(DateUtils.parseDate(column[12] + ' ' + column[13],
				        "yyyy-MM-dd HH:mm:ss", "yyyy/MM/dd HH:mm:ss", "yyyy-MM-dd HH:mm:ss.SSSSSSSSS"));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			this.ip = column[19];
			this.amount = column[5];
			this.currency = "CNY";
			this.channel = column[4];
			if (StringUtils.isNoneBlank(this.file)) {
				this.serverid = this.gameid + '_'
				        + column[2].substring(column[2].indexOf('下') + 1, column[2].lastIndexOf('区'));
			}
			this.createTime = DateUtils.getDateString();
			this.md5 = MD5Utils.encrypt(this.toString());
			this.isValid = true;
		} else {
			this.isValid = false;
		}
	}

}
